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

    /**
     * Get cell from this view's coords.
     * getCell == getCellPush. 
     * TODO: FIXME - only have one, rename to getCellProjected or such.
     *       "getCellPull()" would have no sense - the cell can be taken from the bottom view directly.
     * @returns  null if empty or out of bounds, respective FieldObject otherwise.
     */
    FieldObject getCellProjected(Coords co);
    
    
    
    /**
     *  How is this view rotated compared to the real (bottom) layer?
     */
    int getRotation();

    /**
     *  What direction in this view equals to the given dir from the bottom layer?
     */
    int pullDirection( int dir );
    
    
    /**
     *  Convert coords from the underlying view into this one.
     */
    Coords pullCoords( Coords co );
    
    /**
     *  Convert coords from the this view into the underlying one.
     */
    Coords pushCoords( Coords co );
    
}// interface

