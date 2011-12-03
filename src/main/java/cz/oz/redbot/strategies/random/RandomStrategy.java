package cz.oz.redbot.strategies.random;

import cz.oz.redbot.model.EDirections;
import cz.oz.redbot.model.State;
import cz.oz.redbot.strategies.IDecision;
import java.util.Random;

/**
 * Debugging purposes.
 * 
 * @author Ondrej Zizka
 */
public class RandomStrategy implements IDecision {

    @Override
    public EDirections getDirection(State state) {
        int rand = new Random().nextInt(1000);
        if( rand > 200 )
            return EDirections.AHEAD;
        
        if( rand > 100 )
            return EDirections.RIGHT;
        
        else
            return EDirections.LEFT;
        
    }
    
}// class

