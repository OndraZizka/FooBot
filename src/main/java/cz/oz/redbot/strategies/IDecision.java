package cz.oz.redbot.strategies;

import cz.oz.redbot.model.EDirections;
import cz.oz.redbot.model.State;

/**
 * Whether to go left, right or straight.
 * 
 * @author Ondrej Zizka
 */
public interface IDecision {
    
    public EDirections getDirection( State state );
    
}// class

