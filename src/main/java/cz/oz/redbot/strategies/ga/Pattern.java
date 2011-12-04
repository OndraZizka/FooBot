package cz.oz.redbot.strategies.ga;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 *
 * @author Ondrej Zizka
 */
public class Pattern {
    
    private int wid;
    private int hei;
    
    public int getHei() {        return hei;    }
    public int getWid() {        return wid;    }
    
    private Coords wormHead;
    
    private FieldObject[][] cells;

    
    
    public Pattern( int wid, int hei ) {
        this.wid = wid;
        this.hei = hei;
        
        this.cells = new FieldObject[hei][wid];
    }
    
    
    
    
}// class

