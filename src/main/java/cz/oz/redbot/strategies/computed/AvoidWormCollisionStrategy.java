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

    private static final int MAX_DISTANCE = 6;
    

    @Override
    public Decision getDirection( State state ) {
        
        IView view = getNormalizedView( state );
        
        List<Worm> worms = state.getOtherWorms();
        
        Worm myWorm = state.getMyWorm();
        
        int dontGoLeft  = 0;
        int dontGoAhead = 0;
        int dontGoRight = 0;
        
        for( Worm worm : worms ) {
            
            if( worm.isDead() )  continue;
            
            
            // Imagine a line in worms current direction;
            // Compute whether we will hit it in few rounds.
            
            int dir = view.fixDirection( worm.getDirection() );
            
            Coords wormHead = view.pullCoords( worm.getHead() );
            Coords myWormHead = view.pullCoords( myWorm.getHead() );
            Coords headOffset = myWormHead.getOffsetOf( wormHead );
            
            // Only care about worms crossing our direction (i.e. heading from left to right or right to left).
            if( !   ( dir == 1  &&  headOffset.x <= 0 )
                 || ( dir == 3  &&  headOffset.x >= 0 )  )    continue;
            
            // Distance of worm's trajectory.
            int dist = -headOffset.y;
            if( dist > MAX_DISTANCE ) continue;  // Too far to care.
            if( dist <= 0 ) continue;            // It's behind us.
            
            // Where will the head be in $dist steps?  (side neutral)
            int headXAfterDistMoves = Math.abs( headOffset.x ) - dist;
            
            // If the head won't be there, we want to go there to block the worm!
            if( headXAfterDistMoves >= 0 )
                return new Decision(100, 300, 100);
            
            // Don't go ahead if the worm will be there.
            if( headXAfterDistMoves + worm.getLength() >= 0 )
                dontGoAhead += MAX_DISTANCE - dist +1;
            
            // Go to the opposite side than the worm is heading to (to the place which it will leave).
            if( dir == 1 ){
                dontGoLeft += Math.max( worm.getLength(), MAX_DISTANCE );
            }
            else{
                dontGoRight += Math.max( worm.getLength(), MAX_DISTANCE );
            }
        }
        
        // dontGo*** should be at most 3 * MAX_DISTANCE.
        // We don't want to get this drop to 0, rather somewhere around 40 %.
        return new Decision (
                100 - 60 * dontGoLeft  / MAX_DISTANCE * 3,
                100 - 60 * dontGoAhead / MAX_DISTANCE * 3,
                100 - 60 * dontGoRight / MAX_DISTANCE * 3
        );
        
    }
    
}// class

