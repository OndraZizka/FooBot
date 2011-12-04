package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.Worm;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;
import java.util.List;

/**
 *  This strategy should avoid going in the collision course.
 *  That means, if our current direction would make us hit other worm if it was continuing in it's direction,
 *  we should avoid that direction.
 *  Length of worms is counted.
 * 
 *  @author Ondrej Zizka
 */
public class AvoidWormCollisionStrategy extends StrategySupport implements IDecision {

    @Override
    public Decision getDirection( State state ) {
        
        IView view = getNormalizedView( state );
        
        List<Worm> worms = state.getOtherWorms();
        
        Worm myWorm = state.getMyWorm();
        
        for( Worm worm : worms ) {
            
            // Imagine a line in worms current direction;
            // Compute whether we will hit it in few rounds.
            
            int dir = view.fixDirection( worm.getDirection() );
            
            Coords headOffset = 
                view.fixCoords( myWorm.getHead() ).getOffsetOf(
                        view.fixCoords( worm.getHead() )
                );
            
            worm.getLength();
            
            // TODO
        }
        
        return Decision.NEUTRAL;
    }
    
}// class

