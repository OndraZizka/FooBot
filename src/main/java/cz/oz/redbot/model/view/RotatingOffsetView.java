package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Turns the coords clockwise around the given center.
 * I.e. combination of firstly translating view, then rotating.
 * 
 * Dirs:  0 = same; 1 = 90 deg.; 2 = 180 deg.; 3 = 270 deg.
 * 
 * @author Ondrej Zizka
 */
public final class RotatingOffsetView implements IView {

    private static final Logger log = LoggerFactory.getLogger(RotatingOffsetView.class);

    
    private final IView src;
    
    private final Coords center;
    
    private int dir;

    
    public RotatingOffsetView( IView src, int dir, Coords center ) {
        if( dir < 0 && dir >= 4 )  log.warn("dir is not normalized range 0-4: " + dir);
        this.src = src;
        this.center = center;
        this.dir = RotatingView.fixJavasDumbModulo( dir, 4 );
    }

    public RotatingOffsetView( IView src, int dir ) {
        this( src, dir, Coords.ZERO );
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
        //return (dir - this.dir) % 4;
        return ( 4 + dir - this.getRotation() % 4 ) % 4;
    }
    
    
    

    @Override
    public Coords pushCoords( Coords co ) {
        Coords res = this.transformPush(co);
        return src.pushCoords( res );
    }

    @Override
    public Coords pullCoords( Coords co ) {
        Coords co2 = src.pullCoords(co);
        Coords res = this.transformPull(co2);
        return res;
    }
    
    
    
    @Override
    public Coords transformPush( Coords co ) {
        
        /// First, rotate it.
        Coords off = null;
        switch( this.dir ){
            case 1: off = new Coords( -co.y,  co.x  ); break;
            case 2: off = new Coords( -co.x, -co.y  ); break;
            case 3: off = new Coords(  co.y, -co.x  ); break;
            case 0: off = co; break;
        }
        
        // Then apply it as a offset to the center.
        Coords res = this.center.add( off );
        return res;
    }
    
    
    @Override
    public Coords transformPull(Coords co) {
        Coords off = this.center.getOffsetOf( co );
        
        Coords res = null;
        switch( this.dir ){
            case 1: res = new Coords(  off.y, -off.x  ); break;
            case 2: res = new Coords( -off.x, -off.y  ); break;
            case 3: res = new Coords( -off.y,  off.x  ); break;
            case 0: res = off; break;
        }
        return res;
    }
    
    
    
}// class

