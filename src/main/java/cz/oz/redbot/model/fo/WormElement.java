package cz.oz.redbot.model.fo;

import cz.oz.redbot.model.Coords;

/**
 *
 * @author Ondrej Zizka
 */
public class WormElement extends FieldObject {
    
    private int wormNo;

    public WormElement( Coords coords, int wormNo ) {
        super(coords);
        this.wormNo = wormNo;
    }
    


    @Override
    public boolean makesMeDead() {
        return true;
    }

    @Override
    public boolean makesMeHappy() {
        return false;
    }

    @Override
    public int howMuchILikeIt() {
        return 0;
    }
    
}// class

