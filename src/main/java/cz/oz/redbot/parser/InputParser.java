package cz.oz.redbot.parser;

import cz.oz.redbot.ex.RedBotEx;
import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.FieldObject;
import cz.oz.redbot.model.Flower;
import cz.oz.redbot.model.Ice;
import cz.oz.redbot.model.Playground;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.Wall;
import cz.oz.redbot.model.Worm;
import cz.oz.redbot.model.WormElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Parses input as per http://wiki.brq.redhat.com/RedBot .
 * 
 * @author Ondrej Zizka
 */
public class InputParser {
    
    private final int WORMS_COUNT = 4;
    
    public State parseState( String textInput, int myWormIndex ) throws RedBotEx {
        
        if( myWormIndex >= WORMS_COUNT )
            throw new RedBotEx("I am told to be " + (myWormIndex + 1) + "th worm of " + WORMS_COUNT + ".");
        
        
        // To be returned.
        State state = new State( myWormIndex );

        
        BufferedReader lines = new BufferedReader( new StringReader(textInput) );
        String line = null;
        try {

            // 1st line - round info.
            {
                line = lines.readLine();
                String[] parts = StringUtils.split(line);
                if( parts.length < 3 )
                    throw new RedBotEx("Not enough round info numbers: " + line);

                int roundNo          = Integer.parseInt(parts[0]);
                int roundsMax        = Integer.parseInt(parts[1]);
                int flowersRemaining = Integer.parseInt(parts[2]);

                state.setCounters( state.new Counters(roundNo, roundsMax, flowersRemaining) );
            }
            
            // 2nd line - dimensions.
            {
                line = lines.readLine();
                String[] parts = StringUtils.split(line);
                if( parts.length < 2 )
                    throw new RedBotEx("Not enough dimension info numbers: " + line);

                int wid = Integer.parseInt(parts[0]);
                int hei = Integer.parseInt(parts[1]);

                state.setPlayground( new Playground( wid, hei ) );
            }
            
            // Next four lines are worms.
            for (int wormNo = 0; wormNo < WORMS_COUNT; wormNo++) {
                line = lines.readLine();
                String[] parts = StringUtils.split(line);
                if( parts.length < 6 )
                    throw new RedBotEx("Not enough bot info numbers: " + line);

                int headX = Integer.parseInt( parts[0] );
                int headY = Integer.parseInt( parts[1] );
                int tailX = Integer.parseInt( parts[2] );
                int tailY = Integer.parseInt( parts[3] );
                int frozen = Integer.parseInt( parts[4] );
                int points = Integer.parseInt( parts[5] );
                
                Worm worm = new Worm( wormNo, new Coords(headX, headY), new Coords(tailX, tailY), frozen, points );
                
                /*if( wormNo == whichWormAmI )
                    state.myWorm = worm;
                else*/
                    state.getWorms().add(worm);
            }
            
            
            // The remaining lines are playground.
            Playground pg = state.getPlayground();
            for( int y = 0; y < pg.getHei(); y++ ) {
                
                // Read the line.
                try {
                    line = lines.readLine();
                } catch ( IOException ex ){
                    throw new RedBotEx("Error reading " + (y+1) + "th line of playground.");
                }
                
                // Cell by cell...
                for( int x = 0; x < pg.getHei(); x++ ) {
                    if(' ' == ( line.charAt(x) )) continue;
                    
                    Coords curCoords = new Coords(x, y);
                    
                    FieldObject fieldObject = null;
                    char c = line.charAt(x);
                    
                    // Wall.
                    if( '#' == c )
                        fieldObject = new Wall( curCoords );
                    
                    // Ice.
                    if( '*' == c )
                        fieldObject = new Ice( curCoords );
                    
                    // Flower.
                    if( CharUtils.isAsciiNumeric( c ) )
                        fieldObject = new Flower( curCoords, CharUtils.toIntValue(c) );
                    
                    if( ! CharUtils.isAsciiAlphaLower(c) ) continue;

                    // Worms.
                    // abcd, hijk, opqr, wxyz
                    int pos = "abcd hijk opqr wxyz".indexOf( c );
                    if( pos == -1 )
                        throw new RedBotEx("Invalid character in playground: " + line);
                    
                    int wormNo               = pos / 5;
                    int nextElementDirection = pos % 5;
                    
                    // Create worm element.
                    fieldObject = new WormElement( curCoords, wormNo );
                    Worm curWorm = state.getWorm( wormNo );
                    curWorm.getElements().add( curCoords );
                    if( curCoords.equals( curWorm.getHead() ) )
                        // 0 = up, 1 = right, 2 = down, 3 = left.
                        curWorm.setDirection( nextElementDirection );
                    
                    // Fill the cell with the created object.
                    pg.setCell( curCoords, fieldObject );
                }
            }

            return state;
        }
        catch( IOException ex ){
            throw new RedBotEx("Error parsing input: " + ex.getMessage() );
        }
        catch( NumberFormatException ex ){
            throw new RedBotEx("Invalid number in input: " + line );
        }
        
    }
    
}// class

