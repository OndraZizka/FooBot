package cz.oz.redbot.strategies.random;

import cz.oz.redbot.model.EDirections;
import cz.oz.redbot.model.State;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import java.util.Random;

/**
 * Debugging purposes.
 * 
 * @author Ondrej Zizka
 */
public class RandomStrategy implements IDecision {

    @Override
    public Decision getDirection(State state) {
        int rand = new Random().nextInt(1000);
        
        if( rand > 200 )
            return new Decision(   0, 100,   0 );
        if( rand > 100 )
            return new Decision( 100,   0,   0 );
        else
            return new Decision(   0,   0, 100 );
        
    }
    
}// class

