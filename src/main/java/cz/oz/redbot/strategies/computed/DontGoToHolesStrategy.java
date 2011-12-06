package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.model.view.RotatingView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  TODO.
 * 
 *  Fill the space ahead using some fill algorithm, up to some range.
 *  If it's a small closed space, don't go there.
 * 
 * 
 *  @author Ondrej Zizka
 */
public class DontGoToHolesStrategy extends StrategySupport implements IDecision {
    
    private static final Logger log = LoggerFactory.getLogger( DontGoToHolesStrategy.class );
    
    private static final int SCAN_DEPTH = 5;
    
    
    @Override
    public Decision getDirection( State state ) {
        
        IView aheadView = getNormalizedView( state );
        IView leftView  = new RotatingView( aheadView, 3 );
        IView rightView = new RotatingView( aheadView, 1 );
        
        int wormLen = state.getMyWorm().getLength();
        
        int left  = 100 - getHoleness( leftView,  SCAN_DEPTH, wormLen );
        int ahead = 100 - getHoleness( aheadView, SCAN_DEPTH, wormLen );
        int right = 100 - getHoleness( rightView, SCAN_DEPTH, wormLen );
        
        Decision dec = new Decision( left, ahead, right );
        log.info(" NO-HOLES --> " + dec);
        return dec;
    }

    
    /**
     *  @returns  Is a hole ahoead of us?  0 - no, 50 - a large one, 100 - small one (worm would not fit in)
     */
    private int getHoleness( IView view, int scanDepth, int wormLength ) {
        
        int wid = scanDepth * 2 + 1;
        int hei = scanDepth + 1;
        int middle = scanDepth;
        
        byte[][] cells = new byte[hei][wid];
        int cellsInsideHole = 0;
        int cellsNumScanned = 0;
        
        Set<Coords> cellsToScan = new HashSet();
        cellsToScan.add(Coords.UP);
        

        // In each step, check the cells in queue.
        // If passable, add their unscanned surrounding to the queue, mark it in the array as scanned and continue.
        int scanStep;
        for( scanStep = 0; scanStep < scanDepth; scanStep++ ) {
            
            log.debug("Scan depth {}, cellsToScan {}, cellsInsideHole {}", new Object[]{scanStep, cellsToScan.size(), cellsInsideHole});
            

            // Shift queues.
            Collection<Coords> cellsNow = cellsToScan;
            cellsToScan = new HashSet();
            
            // Scan cells in queue.
            for( Coords co : cellsNow ) {
                cells[ hei + co.y ][ middle + co.x ] = 1;
                cellsNumScanned++;
                if( view.getCellPush( co ).makesMeDead() )
                    continue;
                
                cellsInsideHole++;
                
                // Scan the three cells around.
                for( Coords off : new Coords[]{ Coords.UP, Coords.LEFT, Coords.RIGHT } ) {
                    Coords co2 = co.add( off );
                    // Should we check for boundaries, or rely on our math?
                    log.trace("Checking {}, i.e. cells[ {} + {} ][ {} + {} ] ", new Object[]{ co2, hei , co2.y,  middle, co2.x });
                    if( 0 == cells[ hei + co2.y ][ middle + co2.x ] ){
                        cellsToScan.add( co2 );
                    }
                }
            }
            if( cellsToScan.size() == 0 )
                break;
        }
        
        
        // If it was not a closed hole, let's give it a green light. (?)
        if( scanStep == scanDepth )
            return 0;
        
        //         Two triangles minus one column in the middle they both occupy.  Maybe slightly imprecise.
        int maxScannedCells = (scanDepth+1)*(scanDepth+1) /* (/2 * 2) */  - (scanDepth+1);  // Useless since we have cellsNumScanned.
        
        log.debug("Math.max( 0, {} - {} * 2 ) / {}  // Unused: {}", new Object[]{ cellsInsideHole, wormLength, cellsNumScanned, maxScannedCells });
        return 100 - 100 * Math.max( 0, cellsInsideHole - wormLength * 2 ) / cellsNumScanned;
    }

    
}// class

