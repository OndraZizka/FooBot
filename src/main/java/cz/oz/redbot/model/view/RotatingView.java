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
public final class RotatingView implements IView {
    
    private final IView src;
    
    private final Coords center;
    
    private int dir;

    
    public RotatingView( IView src, int dir, Coords center ) {
        assert dir >= 0 && dir < 4;
        this.src = src;
        this.center = center;
        this.dir = dir % 4;
    }

    public RotatingView( IView src, int dir ) {
        assert dir >= 0 && dir < 4;
        this.src = src;
        this.center = Coords.ZERO;
        this.dir = dir % 4;
    }

    
    
    @Override
    public FieldObject getCell( Coords co ) {
        return this.src.getCell( this.fixCoords(co) );
    }

    @Override
    public int getRotation() {
        return this.src.getRotation() + this.dir;
    }

    @Override
    public int fixDirection(int dir) {
        // Fixing direction is opposite to view rotation.
        return (dir - this.dir) % 4;
    }

    @Override
    public Coords fixCoords( Coords co ) {
        Coords off = this.center.getOffsetOf( co );
        
        Coords res = null;
        switch( this.dir ){
            case 1: res = this.center.add( -off.y, off.x  );
            case 2: res = this.center.add( -off.x, -off.y  );
            case 3: res = this.center.add(  off.y, -off.x  );
            case 0: res = co;
        }
        return res;
    }
    
    
    
}// class

