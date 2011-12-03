package cz.oz.redbot.strategies.ga;

import cz.oz.redbot.model.EDirections;
import cz.oz.redbot.model.PlaygroundView;
import cz.oz.redbot.model.State;
import cz.oz.redbot.strategies.IDecision;
import java.util.List;

/**
 *
 * This strategy looks around worm's head,
 * picks the best matching known pattern,
 * and applies it's outcome.
 * 
 * These outcomes for patterns are partly hard-coded (3x3), partly learned by genetic algorithms.
 * 
 * @author Ondrej Zizka
 */
public class PatternStrategy implements IDecision {
    
    private List<Pattern> patterns;

    
    
    @Override
    public EDirections getDirection(State state) {
        
        this.findBestMatchingPatternAroundWormsHead( state );
        
        return EDirections.AHEAD;
    }

    
    private Pattern findBestMatchingPatternAroundWormsHead( State state ) {
        return findBestMatchingPattern( state.getPlayground().getView( state.getMyWorm().getHead() )  );
    }

    
    private Pattern findBestMatchingPattern( PlaygroundView view ) {
        // TODO;
    }
    
}// class

