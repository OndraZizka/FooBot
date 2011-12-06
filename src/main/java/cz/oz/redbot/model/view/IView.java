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
     * @returns  null if empty or out of bounds, respective FieldObject otherwise.
     */
    FieldObject getCell(Coords co);
    FieldObject getCellPush(Coords co);
    
    int getRotation();
    
    int fixDirection( int dir );
    
    /**
     *  Convert coords from the underlying view into this one.
     */
    Coords pullCoords( Coords co );
    
    /**
     *  Convert coords from the this view into the underlying one.
     */
    Coords pushCoords( Coords co );
    
}// interface

