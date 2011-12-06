package cz.oz.redbot.strategies;

import java.util.Random;

/**
 *  Three values, one for each possible decision.
 *  Each value is percentage recommendation of making that decision.
 *  
 *   0    completely vetoes that decision.
 *   100  doesn't make any change.
 *   200  doubles the decision.
 * 
 *  Integers are used to make it faster.
 * 
 * @author Ondrej Zizka
 */
public final class Decision {


    public final int ahead;
    public final int left;
    public final int right;

    
    public Decision( int left, int ahead, int right ) {
        this.ahead = ahead;
        this.left = left;
        this.right = right;
    }
    
    
    
    public static final Decision ZERO       = new Decision( 0, 0, 0 );
    public static final Decision NEUTRAL    = new Decision( 100, 100, 100 );
    public static final Decision NOT_AHEAD  = new Decision( 100, 0, 100 );
    public static final Decision NOT_LEFT   = new Decision( 0, 100, 100 );
    public static final Decision NOT_RIGHT  = new Decision( 100, 100, 0 );

    
    /**
     * @returns  this * 100 / x.
     */
    public Decision divide100( int x ) {
        return new Decision( left * 100 / x , ahead * 100 / x, right * 100 / x );
    }

    public Decision divide( int x ) {
        return new Decision( left / x , ahead / x, right / x );
    }

    /**
     * @returns  Simply this * x.
     */
    public Decision multiply( int x ) {
        return new Decision( left * x , ahead * x, right * x );
    }

    public Decision multiply100( int x ) {
        return new Decision( left * x / 100 , ahead * x / 100, right * x / 100 );
    }

    public Decision multiply( Decision d ) {
        return new Decision( left * d.left / 100, ahead * d.ahead / 100, right * d.right / 100 );
    }

    /**
     *  Makes numbers over 100 bigger, numbers below 100 lesser.
     */
    public Decision bias( int x ) {
        double xd = x / 100.0;
        return new Decision( (int) (Math.pow( 1.0 * left  / 100.0, xd) * 100), 
                             (int) (Math.pow( 1.0 * ahead / 100.0, xd) * 100),
                             (int) (Math.pow( 1.0 * right / 100.0, xd) * 100) );
    }

    /**
     * @returns  this + d.
     */
    public Decision add( Decision d ) {
        return new Decision( left + d.left, ahead + d.ahead, right + d.right );
    }

    
    
    public char toChar(){
        if( ahead >= left && ahead >= right )
            return '.';
        
        if( left  > ahead && left > right )
            return 'l';
        
        if( right > ahead && right > left )
            return 'r';
        
        if( right == left )
            return new Random().nextBoolean() ? 'l' : 'r';
        
        throw new IllegalStateException("Shouldn't happen - wrongly coded toChar().");
    }

    @Override
    public String toString() {
        return "Decision{ " + left + " | " + ahead + " | " + right + " }";
    }
    
    
}// class

