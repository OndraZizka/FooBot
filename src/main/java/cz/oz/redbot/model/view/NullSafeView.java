package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 *
 * @author Ondrej Zizka
 */
public class NullSafeView extends ViewBase implements IView {

    public NullSafeView( IView src ){
        super(src);
    }
    
    
    @Override
    public Coords transformPush( Coords co ) {
        return co;
    }

    @Override
    public Coords transformPull( Coords co ) {
        return co;
    }
    
    
    @Override
    public FieldObject getCellProjected( Coords co ) {
        FieldObject cell = this.src.getCellProjected(co);
        return cell != null ? cell : FieldObject.EMPTY;
    }
    
    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    
}// class

