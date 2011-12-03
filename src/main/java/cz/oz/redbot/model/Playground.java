package cz.oz.redbot.model;

import cz.oz.redbot.ex.RedBotEx;

/**
 *
 * @author Ondrej Zizka
 */
public class Playground {
    
    private FieldObject[][] cells;
    
    private int wid;
    private int hei;
    
    public Playground( int wid, int hei ){
        this.cells = new FieldObject[hei][wid];
        /*for (int y = 0; y < hei; i++) {
        }*/
        this.wid = wid;
        this.hei = hei;
    }

    public int getHei() {
        return hei;
    }

    public int getWid() {
        return wid;
    }
    
    public boolean isInBounds( Coords co ){
        if( co.x < 0  ||  co.x >= wid )  return false;
        if( co.y < 0  ||  co.y >= hei )  return false;
        return true;
    }

    /**
     *   @returns  null if empty or out of bounds, respective FieldObject otherwise.
     */
    public FieldObject getCell( Coords co ) {
        if( ! isInBounds( co ) )  return null;
        return cells[co.y][co.x];
    }

    public void setCell( Coords co, FieldObject fieldObject) throws RedBotEx {
        if( ! isInBounds( co ) )
            throw new RedBotEx("Playground indexes out of bounds: [" + co.x + ", " + co.y + "]");
        
        this.cells[co.y][co.x] = fieldObject;
    }
    
    /**
     * @returns  View with offset set by given coords.
     */
    public PlaygroundView getView( Coords co ){
        return new PlaygroundView( this, co.x, co.y );
    }
    
}// class

