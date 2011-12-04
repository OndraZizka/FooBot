package cz.oz.redbot.model.fo;

import cz.oz.redbot.model.Coords;

/**
 *
 * @author Ondrej Zizka
 */
public class Wall extends FieldObject {

    public Wall(Coords coords) {
        super(coords);
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

