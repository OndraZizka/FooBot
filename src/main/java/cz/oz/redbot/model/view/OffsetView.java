package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;


/**
 *
 * @author Ondrej Zizka
 */
public final class OffsetView extends ViewBase implements IView {
    
    private final int offX;
    private final int offY;

    
    public OffsetView( IView src, int offX, int offY ) {
        super(src);
        this.offX = offX;
        this.offY = offY;
    }
    
    public OffsetView( IView src, Coords off ) {
        super(src);
        this.offX = off.x;
        this.offY = off.y;
    }
    

    
    @Override
    public Coords transformPush( Coords co ) {
        return co.add( -this.offX, -this.offY );
    }

    @Override
    public Coords transformPull( Coords co ) {
        return co.add( this.offX, this.offY );
    }

    
    
    
    @Override
    public Coords pullCoords( Coords co ) {
        return transformPull( src.pullCoords(co) );
    }

    @Override
    public Coords pushCoords( Coords co ) {
        return src.pushCoords( transformPush(co) );
    }

    
}// class

