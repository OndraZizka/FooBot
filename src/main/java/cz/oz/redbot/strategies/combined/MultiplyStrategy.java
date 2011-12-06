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
public class MultiplyStrategy implements IDecision {
    
    private static final Logger log = LoggerFactory.getLogger( MultiplyStrategy.class );
    
    private static final int PRECISION = 10;
            
    
    private List<IDecision> stretegies = new LinkedList();

    
    /**
     *  Adds.
     */
    public void add( IDecision strategy ){
        this.stretegies.add( strategy );
    }


    /**
     * @returns  Weighted average of contained strategies.
     */
    @Override
    public Decision getDirection( State state ) {
        
        Decision decSum = Decision.NEUTRAL.multiply(PRECISION);
        
        for( IDecision strategy : this.stretegies ) {
            Decision dec = strategy.getDirection( state );
            log.info( strategy.getClass().getSimpleName() + " gives " + dec );
            decSum = decSum.multiply( dec );
        }
        
        return decSum.divide(PRECISION);
    }
    
}// class
