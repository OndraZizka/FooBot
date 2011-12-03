package cz.oz.redbot.model;

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
    
}// class

