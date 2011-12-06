package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 *
 * @author Ondrej Zizka
 */
public abstract class ViewBase implements IView {
    
    protected final IView src;

    public ViewBase( IView src ) {
        this.src = src;
    }
    
    
    @Override
    public FieldObject getCell( Coords co ) {
        return this.src.getCell( this.pullCoords(co) );
    }

    @Override
    public FieldObject getCellPush( Coords co ) {
        return this.src.getCellPush( this.pullCoords(co) );
    }

    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    
    @Override
    public int fixDirection( int dir ){
        return src.fixDirection(dir);
    }

    
    @Override
    public Coords pullCoords( Coords co ) {
        return src.pullCoords(co);
    }
    
    @Override
    public Coords pushCoords( Coords co ) {
        return src.pushCoords(co);
    }
    
    
    
    
    
}// class

