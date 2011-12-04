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
        return this.src.getCell( this.fixCoords(co) );
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
    public Coords fixCoords( Coords co ) {
        return src.fixCoords(co);
    }
    
    
    
    
    
}// class

