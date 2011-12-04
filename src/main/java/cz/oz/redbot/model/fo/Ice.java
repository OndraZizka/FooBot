package cz.oz.redbot.model.fo;

import cz.oz.redbot.model.Coords;

/**
 *
 * @author Ondrej Zizka
 */
public class Ice extends FieldObject {

    public Ice(Coords coords) {
        super(coords);
    }


    @Override
    public boolean makesMeDead() {
        return false;
    }

    @Override
    public boolean makesMeHappy() {
        return true;
    }

    @Override
    public int howMuchILikeIt() {
        return 1500;
    }
    
    
}// class

