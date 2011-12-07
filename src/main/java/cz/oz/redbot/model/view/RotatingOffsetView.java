package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 * Turns the fields around the given center.
 * 
 * Dirs:  0 = same; 1 = 90 deg.; 2 = 180 deg.; 3 = 270 deg.
 * 
 * TODO: Perhaps the transposition could be removed to optimize computation.
 * 
 * @author Ondrej Zizka
 */
public final class RotatingOffsetView implements IView {
    
    private final IView src;
    
    private final Coords center;
    
    private int dir;

    
    public RotatingOffsetView( IView src, int dir, Coords center ) {
        assert dir >= 0 && dir < 4;
        this.src = src;
        this.center = center;
        this.dir = dir % 4;
    }

    public RotatingOffsetView( IView src, int dir ) {
        assert dir >= 0 && dir < 4;
        this.src = src;
        this.center = Coords.ZERO;
        this.dir = dir % 4;
    }

    
    
    @Override
    public FieldObject getCellProjected( Coords co ) {
        return this.src.getCellProjected( this.pullCoords(co) );
    }

    /*@Override
    public FieldObject getCellPush( Coords co ) {
        return this.src.getCell( this.pushCoords(co) );
    }*/

    @Override
    public int getRotation() {
        return this.src.getRotation() + this.dir;
    }

    @Override
    public int pullDirection(int dir) {
        // Fixing direction is opposite to view rotation.
        return (dir - this.dir) % 4;
    }

    @Override
    public Coords pullCoords( Coords co ) {
        Coords co2 = src.pullCoords(co); // I'm not sure whether I should apply it before or after... Getting lost in that all.
                                         // Most likely before, as it's now.
        Coords off = this.center.getOffsetOf( co2 );
        
        Coords res = null;
        switch( this.dir ){
            case 1: res = new Coords(  off.y, -off.x  ); break;
            case 2: res = new Coords( -off.x, -off.y  ); break;
            case 3: res = new Coords( -off.y,  off.x  ); break;
            case 0: res = off; break;
        }
        return res;
    }
    
    
    @Override
    public Coords pushCoords( Coords co ) {
        
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

        // return res;
        return src.pushCoords( res );
        
    }
    
    
    
}// class

