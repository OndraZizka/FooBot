package cz.oz.redbot;

import cz.oz.redbot.ex.RedBotEx;
import cz.oz.redbot.model.State;
import cz.oz.redbot.parser.InputParser;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.combined.CombinedStrategy;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * RedBot impl.
 * See http://wiki.brq.redhat.com/RedBot .
 */
public class RedBotApp 
{
    public static void main( String[] args ) throws IOException, RedBotEx {
        
        if( args.length < 2 )
            throw new IllegalArgumentException("Expecting 2 args - game state file path name and worm ID.");
        
        File stateFile = new File( args[0] ); 
        if( ! stateFile.exists() )
            throw new IllegalArgumentException("File not found: " + stateFile.getAbsolutePath() );
        
        int myWormID = -1;
        try {
            myWormID = Integer.parseInt(args[1]);
        }
        catch ( NumberFormatException ex ){
            throw new IllegalArgumentException("Invalid worm ID, expecting number: " + args[1]);
        }
        
        String textInput = FileUtils.readFileToString( stateFile );
        State state = new InputParser().parseState( textInput, myWormID);
        
        Decision direction = new CombinedStrategy().getDirection( state );
        
        // l, r, .
        System.out.println( direction.toChar() );
        
    }// main()
    
}// class
