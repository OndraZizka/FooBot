/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.oz.redbot.model.view;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.fo.FieldObject;

/**
 *
 * @author Ondrej Zizka
 */
public interface IView {

    
    // Core two methods.
    
    /**
     * Only perform tranformation of this view.
     * @return  coords from this view projected to the view below.
     */
    Coords transformPush( Coords co );
    
    /**
     * Only perform reversed tranformation of this view.
     * @return  coords from the view below projected to this view.
     */
    Coords transformPull( Coords co );

    // Recursive methods.

    /**
     *  Convert coords from the underlying view into this one.
     */
    Coords pullCoords( Coords co );
    
    /**
     *  Convert coords from the this view into the underlying one.
     */
    Coords pushCoords( Coords co );

    
    /**
     *  How is this view rotated compared to the real (bottom) layer?
     */
    int getRotation();

    /**
     *  What direction in this view equals to the given dir from the bottom layer?
     */
    int pullDirection( int dir );

    
    // Util recursive methods.
    
    /**
     * Get cell from this view's coords.
     * "getCellPull()" would have no sense - the cell can be taken from the bottom view directly.
     * 
     * @returns  null if empty or out of bounds, respective FieldObject otherwise.
     */
    FieldObject getCellProjected( Coords co );
    
    
    
    
    
}// interface

