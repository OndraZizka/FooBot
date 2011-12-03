package cz.oz.redbot.model;

/**
 *
 * @author Ondrej Zizka
 */
public class PlaygroundView {
    
    private final Playground pg;
    
    private final int offX;
    
    private final int offY;

    
    public PlaygroundView( Playground pg, int offX, int offY ) {
        this.pg = pg;
        this.offX = offX;
        this.offY = offY;
    }
    
    
    /**
     *   @returns  null if empty or out of bounds, respective FieldObject otherwise.
     */
    public FieldObject getCell( Coords co ){
        
        return pg.getCell( co );
                
    }
    
}// class

