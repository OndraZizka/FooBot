package cz.oz.redbot.strategies.combined;

import cz.oz.redbot.model.State;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.computed.AvoidFullSpacesStrategy;
import cz.oz.redbot.strategies.computed.AvoidWormCollisionStrategy;
import cz.oz.redbot.strategies.computed.DontGoToHolesStrategy;
import cz.oz.redbot.strategies.computed.NoBrainersStrategy;
import cz.oz.redbot.strategies.computed.PursuitStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 *
 * @author Ondrej Zizka
 */
public class CombinedStrategy implements IDecision {
    
    private static final Logger log = LoggerFactory.getLogger( CombinedStrategy.class );
    
    private static final int PRECISION = 10;
            
    
    private final IDecision combinedStrategy;

    public CombinedStrategy() {

        // Weighted avg.
        WeightedAverageStrategy wa = new WeightedAverageStrategy();
        wa.add( 150, new PursuitStrategy() );
        wa.add( 100, new AvoidFullSpacesStrategy() );
        wa.add( 100, new AvoidWormCollisionStrategy() );
        //new BlockWormStrategy() ); //  NYI
        //new PatternStrategy() ); //  NYI
        
        // Multiplying strategies.
        // We multiply by those which are the most important and their decision is safe and reliable 
        // (i.e. when they veto some direction by setting it 0, we will apply that veto).
        MultiplyStrategy mul = new MultiplyStrategy();
        mul.add( wa );
        mul.add( new NoBrainersStrategy() );
        mul.add( new DontGoToHolesStrategy() );

        this.combinedStrategy = mul;
    }
    


    /**
     * @returns  Weighted average of contained strategies.
     */
    @Override
    public Decision getDirection( State state ) {
        
        Decision decision = this.combinedStrategy.getDirection( state );
        log.info(" COMBINED --> " + decision);
        return decision;
    }
    
}// class
