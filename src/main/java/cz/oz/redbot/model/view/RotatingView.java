package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Turns the fields around the given center.
 * 
 * Dirs:  0 = same; 1 = 90 deg.; 2 = 180 deg.; 3 = 270 deg.
 * 
 * TODO: Perhaps the transposition could be removed to optimize computation.
 * 
 * @author Ondrej Zizka
 */
public final class RotatingView implements IView {
    
    private static final Logger log = LoggerFactory.getLogger(RotatingView.class);
    
    
    private final IView src;
    
    private int dir;

    
    public RotatingView( IView src, int dir ) {
        //assert dir >= 0 && dir < 4;
        if( dir < 0 && dir >= 4 )  log.warn("dir is not normalized range 0-4: " + dir);
        this.src = src;
        //this.dir = dir % 4;  // -3 % 4  ==  -3
        //this.dir = ((dir % 4) + 4) % 4;
        this.dir = fixJavasDumbModulo( dir, 4 );
    }
    
    static int fixJavasDumbModulo( int val, int mod ){
        return ((val % mod) + mod) % mod;
    }


    
    // Core two methods.
    
    @Override
    public Coords transformPush( Coords co ) {
        Coords res = null;
        switch( this.dir ){
            case 1: res = new Coords( -co.y,  co.x  ); break;
            case 2: res = new Coords( -co.x, -co.y  ); break;
            case 3: res = new Coords(  co.y, -co.x  ); break;
            case 0: res = co; break;
        }
        return res;
    }

    @Override
    public Coords transformPull( Coords co ) {
        Coords res = null;
        switch( this.dir ){
            case 1: res = new Coords(  co.y, -co.x  ); break;
            case 2: res = new Coords( -co.x, -co.y  ); break;
            case 3: res = new Coords( -co.y,  co.x  ); break;
            case 0: res = co; break;
        }
        return res;
    }
    
    
    
    
    @Override
    public FieldObject getCellProjected( Coords co ) {
        return this.src.getCellProjected( this.transformPush(co) );
    }

    @Override
    public int getRotation() {
        return (this.src.getRotation() + this.dir) % 4;
    }

    @Override
    public int pullDirection(int dir) {
        // Fixing direction is opposite to view rotation.
        return ( 4 + dir - this.getRotation() % 4 ) % 4;
        //fixJavasDumbModulo( ( dir - this.dir), 4 );
    }

    @Override
    public Coords pullCoords( Coords co ) {
        Coords co2 = src.pullCoords(co);
        return transformPull(co2);
    }
    
    
    @Override
    public Coords pushCoords( Coords co ) {
        Coords res = transformPush( co );
        return src.pushCoords( res );
    }

    
    @Override
    public String toString() {
        return "RotatingView{ dir=" + dir + ", src=" + src + " }";
    }

}// class

