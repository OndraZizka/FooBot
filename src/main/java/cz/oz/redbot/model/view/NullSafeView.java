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
    public FieldObject getCell( Coords co ) {
        FieldObject cell = this.src.getCell(co);
        return cell != null ? cell : FieldObject.EMPTY;
    }
    
    @Override
    public FieldObject getCellPush( Coords co ) {
        FieldObject cell = this.src.getCellPush(co);
        return cell != null ? cell : FieldObject.EMPTY;
    }
    
    @Override
    public int getRotation() {
        return this.src.getRotation();
    }
    
    
}// class

