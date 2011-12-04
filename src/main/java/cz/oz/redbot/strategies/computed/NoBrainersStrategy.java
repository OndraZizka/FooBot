package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.fo.FieldObject;
import cz.oz.redbot.model.fo.Wall;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.model.view.NullSafeView;
import cz.oz.redbot.model.view.RotatingView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;

/**
 *  This strategy only cares about nearest 3 cells.
 *  It should ensure that we pick up the nearest bounties,
 *  but avoid hitting a wall right next or ahead of us.
 * 
 * @author Ondrej Zizka
 */
public class NoBrainersStrategy extends StrategySupport implements IDecision {

    
    @Override
    public Decision getDirection( State state ) {
        
        // Move view to worm's head
        // and rotate the view to act like worm is always heading up.
        IView view = new RotatingView( state.getPlayground(), state.getMyWorm().getDirection(), state.getMyWorm().getHead() );
        view = new NullSafeView(view);
        
        return new Decision(
                view.getCell( Coords.LEFT ).howMuchILikeIt(),
                view.getCell( Coords.UP ).howMuchILikeIt(),
                view.getCell( Coords.RIGHT ).howMuchILikeIt()
        );
        
    }// getDirection()
    
    
    
}// class

