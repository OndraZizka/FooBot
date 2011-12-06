package cz.oz.redbot.model;

import cz.oz.redbot.model.fo.FieldObject;
import java.util.Comparator;

/**
 *
 * @author Ondrej Zizka
 */
final class DistanceComparator implements Comparator<FieldObject> {
    
    private Coords center;

    
    public DistanceComparator( Coords center ) {
        this.center = center;
    }

    

    @Override
    public int compare( FieldObject o1, FieldObject o2 ) {
        Coords off1 = this.center.getOffsetOf( o1.getCoords() );
        Coords off2 = this.center.getOffsetOf( o2.getCoords() );
        
        // a^2 + b^2
        return 
                off1.x * off1.x + off1.y * off1.y 
                -
                off2.x * off2.x + off2.y * off2.y;
    }
    
}// class

