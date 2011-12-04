package cz.oz.redbot.model.fo;

import cz.oz.redbot.model.Coords;

/**
 *
 * @author Ondrej Zizka
 */
public abstract class FieldObject {
    
    private Coords coords;

    public Coords getCoords() {        return coords;    }
    public void setCoords(Coords coords) {        this.coords = coords;    }

    public FieldObject(Coords coords) {
        this.coords = coords;
    }
    
    public abstract boolean makesMeDead();
    public abstract boolean makesMeHappy();
    
    /**
     * @returns  Value from 0 to 2000, telling us how much we want to get to such cell.
     *           0 means we don't want go there at all,
     *           100 is neutral (e.g. empty cell),
     *           200 - 1100 are flowers,
     *           somewhere between 1000 and 2000 is an ice (TBD decide how much).
     */
    public abstract int howMuchILikeIt();
    
    
    
    /**
     *   Static instance to deal with null fields.
     */
    public static final FieldObject EMPTY = new FieldObject( Coords.ZERO ) {

        @Override
        public boolean makesMeDead() {
            return false;
        }

        @Override
        public boolean makesMeHappy() {
            return false;
        }

        @Override
        public int howMuchILikeIt() {
            return 100;
        }
    };
    
    
}// class

