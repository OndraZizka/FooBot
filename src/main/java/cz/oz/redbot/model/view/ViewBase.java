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
    public FieldObject getCellProjected( Coords co ) {
        return this.src.getCellProjected( this.pullCoords(co) );
    }

    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    
    @Override
    public int pullDirection( int dir ){
        return src.pullDirection(dir);
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

