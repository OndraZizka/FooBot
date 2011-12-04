package cz.oz.redbot.model.fo;

import cz.oz.redbot.model.Coords;

/**
 *
 * @author Ondrej Zizka
 */
public class Flower extends FieldObject {
    
    int points = 1;

    public Flower( Coords co, int points) {
        super( co );
        this.points = points;
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
        return 100 + points * 100;
    }
    
}// class

