package cz.oz.redbot.model;

import cz.oz.redbot.model.fo.FieldObject;
import cz.oz.redbot.ex.RedBotEx;
import cz.oz.redbot.model.view.IView;

/**
 *
 * @author Ondrej Zizka
 */
public class Playground implements IView {
    
    private FieldObject[][] cells;
    
    private int wid;
    private int hei;
    
    public Playground( int wid, int hei ){
        this.cells = new FieldObject[hei][wid];
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
    @Override
    public FieldObject getCellProjected( Coords co ) {
        if( ! isInBounds( co ) )  return null;
        return cells[co.y][co.x];
    }


    public void setCell( Coords co, FieldObject fieldObject) throws RedBotEx {
        if( ! isInBounds( co ) )
            throw new RedBotEx("Playground indexes out of bounds: [" + co.y + ", " + co.x + "]");
        
        this.cells[co.y][co.x] = fieldObject;
    }
    
    
    
    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public int pullDirection(int dir) {
        return dir;
    }

    @Override
    public Coords pullCoords(Coords co) {
        return co;
    }
    
    @Override
    public Coords pushCoords(Coords co) {
        return co;
    }

    @Override
    public Coords transformPush(Coords co) {
        return co;
    }

    @Override
    public Coords transformPull(Coords co) {
        return co;
    }
    
    
}// class

