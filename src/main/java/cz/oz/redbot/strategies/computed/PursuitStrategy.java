package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.PgTools;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.Worm;
import cz.oz.redbot.model.fo.FieldObject;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.model.view.NullSafeView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Navigates the worm to the best flower or ice (bounty).
 *  Computation is based on distance and value of the bounty.
 *  A worm near the bounty lowers it's attractivity.
 * 
 * @author Ondrej Zizka
 */
public class PursuitStrategy extends StrategySupport implements IDecision {
    
    private static final Logger log = LoggerFactory.getLogger( PursuitStrategy.class );
    

    @Override
    public Decision getDirection( State state ) {
        
        Coords myHead = state.getMyWorm().getHead();
        
        // Merge flowers and ices.
        List<FieldObject> fieldObjects = new LinkedList( state.getFlowers() );
        fieldObjects.addAll( state.getIces() );
        
        List<FieldObjectWithAttractivity> fowas = new ArrayList( fieldObjects.size() );
        
        // For each flower or ice...
        for( FieldObject fo : fieldObjects ) {
            //log.info("FieldObject: " + fo.toString() );
            
            int myDist = myHead.distanceStepsOf( fo.getCoords() );
            assert myDist != 0; // It shouldn't be under our head.
            
            int attractivity = fo.howMuchILikeIt() / myDist;
            
            // Check how full the space between us and the field object is.
            int fullness = PgTools.getAreaFullness( new NullSafeView( state.getPlayground() ), myHead, fo.getCoords() );
            int attrCorrected  = attractivity * (100 - fullness) / 100;
            int attrCorrected2 = attrCorrected;
            
            // Check if there's other worm's head near that.
            for( Worm worm : state.getOtherWorms() ){
                int wormDist = worm.getHead().distanceStepsOf( fo.getCoords() );
                int heIsCloser = 100 * wormDist / myDist;
                if( heIsCloser < 100 ){
                    int reduction = 40 + 60 * heIsCloser / 100;  // 40 to 100.
                    log.trace(" Worm "+worm+" is closer: " + heIsCloser + ";   reduction: " + reduction);
                    attrCorrected2 = attrCorrected2 * reduction / 100;  // Maybe too strict? They can be stupid and miss it. Or behind a wall.
                }
            }
            
            log.trace("FieldObject: " + fo.toString() + "  attr: " + attractivity + "  fullness: " + fullness + "  attrCorr: " + attrCorrected + "  attrCorr2: " + attrCorrected2 );
            fowas.add( new FieldObjectWithAttractivity( fo, attrCorrected2 ));
            
        }
        
        // Now traverse through sortedFOs and increment the decision to go in it's direction.
        // Use transformed view.
        IView view = getNormalizedView( state );
        
        Decision sum = Decision.ZERO;
        
        // Get a decision for each of the flowers and compute the average (of all 3 options).
        for( FieldObjectWithAttractivity fowa : fowas ) {
            Coords foCoords = view.pullCoords( fowa.fo.getCoords() );
            Decision dir = PgTools.getDirectionDecisionTowards( foCoords );
            log.trace(" Dir towards {} -> {} : {}", new Object[]{ fowa.fo.getCoords(), foCoords, dir} );
            //Decision dirCorrected = dir.multiply100( 100 + fowa.attractivity );
            Decision dirCorrected = dir.bias( 100 + fowa.attractivity );
            sum = sum.add( dirCorrected );
        }
       
        return sum.divide( fowas.size() );
    }
    
}// class



/**
 *  Used for sorting.
 */
class FieldObjectWithAttractivity implements Comparable<FieldObjectWithAttractivity> {
    FieldObject fo;
    int attractivity;

    public FieldObjectWithAttractivity(FieldObject fo, int attractivity) {
        this.fo = fo;
        this.attractivity = attractivity;
    }

    
    @Override
    public int compareTo( FieldObjectWithAttractivity o ) {
        int diff = o.attractivity - this.attractivity;
        if( diff != 0 ) return diff;
        else return this.hashCode() - o.hashCode();
    }
    

    @Override
    public String toString() {
        return "FieldObjectWithAttractivity{ " + attractivity + ", " + fo + " }";
    }
}
