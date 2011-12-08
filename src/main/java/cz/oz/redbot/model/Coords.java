package cz.oz.redbot.model;

/**
 *
 * @author Ondrej Zizka
 */
public class Coords {

    public static final Coords ZERO  = new Coords(0, 0);
    public static final Coords UP    = new Coords(  0, -1 );
    public static final Coords DOWN  = new Coords(  0,  1 );
    public static final Coords LEFT  = new Coords( -1,  0 );
    public static final Coords RIGHT = new Coords(  1,  0 );
    
    public int x;
    public int y;

    public Coords( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Coords getOffsetOf( Coords co ) {
        return new Coords( co.x - this.x, co.y - this.y );
    }

    public Coords add( int x, int y ) {
        return new Coords( this.x + x, this.y + y);
    }
    
    public Coords add( Coords co ) {
        return this.add( co.x, co.y );
    }

    public Coords invert() {
        return new Coords( -x, -y );
    }

    

    /**  Optimization - no need for square root (e.g. for comparison). */
    public int distancePowerOf( Coords co ) {
        Coords off = this.getOffsetOf( co );
        return ( off.x * off.x + off.y * off.y );
    }

    /** In a grid, distances are measured by steps to get there. */
    public int distanceStepsOf( Coords co ) {
        Coords off = this.getOffsetOf( co );
        return ( Math.abs(off.x) + Math.abs(off.y) );
    }

    @Override
    public String toString() {
        return String.format("[%02d,%02d]", x, y);
    }

    
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)  return false;
        if (getClass() != obj.getClass())  return false;
        final Coords other = (Coords) obj;
        if (this.x != other.x)  return false;
        if (this.y != other.y)  return false;
        return true;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    
}// class

