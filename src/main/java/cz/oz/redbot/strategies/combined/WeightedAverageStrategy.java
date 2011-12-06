package cz.oz.redbot.strategies.combined;

import cz.oz.redbot.model.State;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ondrej Zizka
 */
public class WeightedAverageStrategy implements IDecision {
    
    private static final Logger log = LoggerFactory.getLogger( WeightedAverageStrategy.class );
    
    
    private List<StragegyWithWeight> stretegies = new LinkedList();

    
    /**
     *  Adds.
     */
    public void add( int weight, IDecision strategy ){
        this.stretegies.add( new StragegyWithWeight( weight, strategy ) );
    }


    /**
     * @returns  Weighted average of contained strategies.
     */
    @Override
    public Decision getDirection( State state ) {
        
        int weightSum = 0;
        Decision decSum = Decision.ZERO;
        
        for( StragegyWithWeight tuple : this.stretegies ) {
            Decision dec = tuple.strategy.getDirection( state );
            log.info( tuple.strategy.getClass().getSimpleName() + " gives " + dec );
            decSum = decSum.add( dec.multiply( tuple.weight ) );
            weightSum += tuple.weight;
        }
        
        return decSum.divide(weightSum);
    }
    
}// class



class StragegyWithWeight {
    final IDecision strategy;
    final int weight;

    public StragegyWithWeight( int weight, IDecision strategy ) {
        this.strategy = strategy;
        this.weight = weight;
    }
}
