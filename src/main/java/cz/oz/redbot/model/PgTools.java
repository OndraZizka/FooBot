package cz.oz.redbot.model;

import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.strategies.Decision;

/**
 *
 * @author Ondrej Zizka
 */
public final class PgTools {

    /**
     * @returns  0 - 100, meaning percentage ratio of full / total cells.
     */
    public static int getAreaFullness( IView view, Coords a, Coords b ){
        
        int cellsTotal = 0;
        int cellsFull  = 0;
        
        for( int y = Math.min(a.y, b.y);  y <= Math.max(a.y, b.y);  y++ ) {
            for( int x = Math.min(a.x, b.x);  x <= Math.max(a.x, b.x);  x++ ) {
                cellsTotal++; // Not optimal but I was lazy coding the math.
                if( view.getCellProjected( new Coords(x,y) ).makesMeDead() )
                    cellsFull++;
            }
        }
        
        return 100 * cellsFull / cellsTotal;
    }

    
    /**
     *  Whether we should go left, ahead or right to get to given coords.
     *  The preferred direction gets up to 300.
     * 
     *  This feels a bit wrong, I should re-think the scoring overall.
     */
    public static Decision getDirectionDecisionTowards( Coords co ) {
        if( co.x == 0 )
            return new Decision( 100, 300, 100 );
        
        double angle    = Math.atan2( -co.y, co.x ); // atan2:  0.0 == "to the right",  Y increases upwards.
                                                     // In this game, Y increaseses downwards.
        double angleDeg = Math.toDegrees(angle);     // Degrees.
        
        // We want it most preferred at sides, and 0 at 90 deg (ahead of us).
        //double preferSides = ( 0.5 * (-Math.cos(angle) +1) ); // Math.cos( angle );
        double preferSides = ( 0.5 * (Math.cos(2.0 * angle) +1) ); // Math.cos( angle );
        int preferenceOfTurning = angle <  0 ? 200 : (int) (200 * preferSides);
        int preferenceOfAhead   = 200 - preferenceOfTurning;
        
        if( co.x < 0 )
            return new Decision( 100 + preferenceOfTurning, 100 + preferenceOfAhead, 80 );
        else
            return new Decision( 80,                        100 + preferenceOfAhead, 100 + preferenceOfTurning );
    }
    
}// class

