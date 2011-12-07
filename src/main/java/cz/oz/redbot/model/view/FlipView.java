package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 * Turns the fields around the given center.
 * 
 * Dirs:  0 = same; 1 = 90 deg.; 2 = 180 deg.; 3 = 270 deg.
 * 
 * @author Ondrej Zizka
 */
public final class FlipView extends ViewBase implements IView {
    
    private final Coords center;
    
    private final boolean horiz;

    
    public FlipView( IView src, Coords center, boolean horiz ){
        super(src);
        this.center = center;
        this.horiz = horiz;
    }
    
    
    @Override
    public FieldObject getCellProjected( Coords co ) {
        
        Coords off = center.getOffsetOf( co );
        
        Coords res = null;
        if( this.horiz )  res = center.add(  off.x, -off.y );
        else              res = center.add( -off.x,  off.y );
        return src.getCellProjected( res );
    }
    
    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    
}// class

