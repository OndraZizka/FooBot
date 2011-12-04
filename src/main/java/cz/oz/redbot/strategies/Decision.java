package cz.oz.redbot.strategies;

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
    
    
    
    public static final Decision NEUTRAL    = new Decision( 100, 100, 100 );
    public static final Decision NOT_AHEAD  = new Decision( 100, 0, 100 );
    public static final Decision NOT_LEFT   = new Decision( 0, 100, 100 );
    public static final Decision NOT_RIGHT  = new Decision( 100, 100, 0 );

    
    
}// class

