package cz.oz.redbot.strategies;

import cz.oz.redbot.model.State;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.model.view.NullSafeView;
import cz.oz.redbot.model.view.RotatingView;

/**
 *  Methods shared by most strategy implementations.
 * 
 *  @author Ondrej Zizka
 */
public class StrategySupport {

    
    /**
     *   Move view to worm's head
     *   and rotate the view to act like worm is always heading up.
     *   Return
     */
    public static IView getNormalizedView( State state )
    {
        IView view = new RotatingView( state.getPlayground(), state.getMyWorm().getDirection(), state.getMyWorm().getHead() );
        view = new NullSafeView(view);
        return view;
    }
    
    
}// class

