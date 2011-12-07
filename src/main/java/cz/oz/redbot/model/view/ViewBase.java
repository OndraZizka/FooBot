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
    
    
    // Core two methods - to be overriden.
    //public Coords transformPush( Coords co ) { }
    //public Coords transformPull( Coords co ) { }

    
    // Recursive methods.
    
    @Override
    public Coords pullCoords( Coords co ) {
        Coords co2 = src.pullCoords(co);
        return transformPull(co2);
    }
    
    
    @Override
    public Coords pushCoords( Coords co ) {
        Coords res = transformPush( co );
        return src.pushCoords( res );
    }

    
    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    @Override
    public int pullDirection( int dir ){
        return src.pullDirection(dir);
    }

    
    // Util recursive methods.
    
    @Override
    public FieldObject getCellProjected( Coords co ) {
        return this.src.getCellProjected( this.transformPush(co) );
    }

    
}// class

