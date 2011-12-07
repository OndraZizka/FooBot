package cz.oz.redbot.model.fo;

import cz.oz.redbot.ex.RedBotEx;
import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.Worm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ondrej Zizka
 */
public class WormElement extends FieldObject {
    
    private static final Logger log = LoggerFactory.getLogger( WormElement.class );
    
    
    final private Worm worm;
    
    final int nextElementDirection;
    
    final State state;  // Needing a state is a hack - I don't want to store worm chains in state for now. TODO?

    
    
    public WormElement( Coords coords, Worm worm, int nextElementDirection, State state ) {
        super(coords);
        this.worm = worm;
        this.state = state;
        this.nextElementDirection = nextElementDirection;
    }

    
    
    /**
     *  Goes from the tail to the head, looking for matching worm element.
     * @return
     * @throws RedBotEx 
     */
    public int howMuchTillTheEnd() throws RedBotEx{
        int counter = 1;
        
        if( this.equals( worm.getTail() ) )
            return 1;
        
        if( this.equals( worm.getHead() ) )
            return worm.getLength();
        
        // Go from tail forward.
        log.trace(" Worm tail: " + worm.getTail());
        Coords curElmCoords = worm.getTail();
        
        while( ! curElmCoords.equals( worm.getHead() ) ){
            
            log.trace("   Scanning " + curElmCoords + ", should be a worm elm.");
                    
            FieldObject cell = state.getPlayground().getCellProjected( curElmCoords );
            if( ! ( cell instanceof WormElement) )
                throw new RedBotEx("No more worm elements, reached head?: " + cell + "  Was looking for: " + this.getCoords());
            
            WormElement we = (WormElement) cell;
            Coords nextOff = null;
            switch( we.nextElementDirection ){
                case 0: nextOff = Coords.UP;    break;
                case 1: nextOff = Coords.RIGHT; break;
                case 2: nextOff = Coords.DOWN;  break;
                case 3: nextOff = Coords.LEFT;  break;
            }
            curElmCoords = curElmCoords.add( nextOff );
            log.trace("    Next cell: {} -> {} -> {}", new Object[]{ we.nextElementDirection, nextOff, curElmCoords } );
            
            counter++;
            if( curElmCoords.equals( this.getCoords() ) )
                return counter;
            
            if( counter >= worm.getLength() )
                throw new RedBotEx("Invalid state - worm's length doesn't match elements: " + this + "  Worm: " + worm);
                
        }
        throw new RedBotEx("Invalid state - worm element not found while traversing worm's body: " + this);
    }


    
    
    @Override
    public boolean makesMeDead() {
        return true;
    }

    @Override
    public boolean makesMeHappy() {
        return false;
    }

    @Override
    public int howMuchILikeIt() {
        return 0;
    }
    
}// class

