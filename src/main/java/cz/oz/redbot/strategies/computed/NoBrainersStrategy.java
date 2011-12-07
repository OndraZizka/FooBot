package cz.oz.redbot.strategies.computed;

import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  This strategy only cares about nearest 3 cells.
 *  It should ensure that we pick up the nearest bounties,
 *  but avoid hitting a wall right next or ahead of us.
 * 
 * @author Ondrej Zizka
 */
public class NoBrainersStrategy extends StrategySupport implements IDecision {

    private static final Logger log = LoggerFactory.getLogger(NoBrainersStrategy.class);
    
    
    @Override
    public Decision getDirection( State state ) {
        
        // Move view to worm's head
        // and rotate the view to act like worm is always heading up.
        IView view = getNormalizedView(state);
        
        log.info(" NO-BRAINER: "
                + "\n                         {} {} {} "
                + "\n                         {} {} {} "
                + "\n                         {} {} {} ",
                new Object[]{
                    Coords.LEFT,   view.getCellProjected( Coords.LEFT ),   view.pushCoords(Coords.LEFT),
                    Coords.UP,     view.getCellProjected( Coords.UP ),     view.pushCoords(Coords.UP),
                    Coords.RIGHT,  view.getCellProjected( Coords.RIGHT ),  view.pushCoords(Coords.RIGHT)
                }
        );
        Decision dec = new Decision(
                                    view.getCellProjected( Coords.LEFT ).howMuchILikeIt(),
                                    view.getCellProjected( Coords.UP ).howMuchILikeIt(),
                                    view.getCellProjected( Coords.RIGHT ).howMuchILikeIt()
                            );
        log.info(" NO-BRAINER --> " + dec);
        return dec;
        
    }// getDirection()
    
    
    
}// class

