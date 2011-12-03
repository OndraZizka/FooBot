package cz.oz.redbot.model;

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
    
}// class

