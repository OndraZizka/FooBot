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

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords getOffsetOf(Coords co) {
        return new Coords( x - this.x, y - this.y );
    }

    public Coords add( int i, int x ) {
        return new Coords( this.x + x, this.y + y);
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

