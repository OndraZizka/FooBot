package cz.oz.redbot.strategies.ga;

import cz.oz.redbot.model.State;
import cz.oz.redbot.model.view.IView;
import cz.oz.redbot.strategies.Decision;
import cz.oz.redbot.strategies.IDecision;
import cz.oz.redbot.strategies.StrategySupport;
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
public class PatternStrategy extends StrategySupport implements IDecision {
    
    private List<Pattern> patterns;

    
    
    @Override
    public Decision getDirection( State state ) {
        
        this.findBestMatchingPatternAroundWormsHead( state );
        
        return new Decision( 0, 100, 0 );
    }

    
    private Pattern findBestMatchingPatternAroundWormsHead( State state ) {
        
        IView view = getNormalizedView(state);
                
        return findBestMatchingPattern( view );
    }

    
    private Pattern findBestMatchingPattern( IView view ) {
        
        return null;
        // TODO;
    }
    
}// class

