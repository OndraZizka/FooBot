package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.model.view.RotatingView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;

/**
 *  Checks how much "makesMeDead" cells there is in given direction.
 * 
 *  @author Ondrej Zizka
 */
public class AvoidFullSpacesStrategy extends StrategySupport implements IDecision {

    @Override
    public Decision getDirection( State state ) {
        
        IView aheadView = getNormalizedView( state );
        IView leftView  = new RotatingView( aheadView, 3 );
        IView rightView = new RotatingView( aheadView, 1 );
        
        int left  = 100 - getFullness( leftView,  5 );
        int ahead = 100 - getFullness( aheadView, 5 );
        int right = 100 - getFullness( rightView, 5 );
        
        return new Decision( left, ahead, right );
    }

    
    /**
     * @param view        View to scan ahead.
     * @param distance    How many rows ahead to scan.
     * @return            0 to 100, meaning ratio of free / full cells  (100 = all full).
     */
    private int getFullness( IView view, int distance ) {
        int count = 0;
        int countFull = 0;
        
        // Scan rows ahead, in a shape of a triangle.
        for (int y = 1; y <= distance; y++) {

            // Cut corners.
            int spread = y;

            for (int x = -spread; x <= spread; x++) {
                Coords cell = new Coords( x, -y ); 
                if( view.getCell( cell ).makesMeDead() )
                    countFull++;
                count++;
            }
        }
        return 100 * count / countFull;
    }
    
}// class

