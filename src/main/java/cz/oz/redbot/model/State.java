package cz.oz.redbot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Ondrej Zizka
 */
public class State {
    
    // Playground.
    public Playground playground;
    public Playground getPlayground() {        return playground;    }
    public void setPlayground(Playground playground) {        this.playground = playground;    }
    
    
    private SortedSet<Flower> flowersByDistance = new TreeSet();
    
    private SortedSet<Ice> icesByDistance = new TreeSet();
    
    //public Worm myWorm;
    //public Worm getMyWorm(){ return this.myWorm; }
    
    public List<Worm> worms = new ArrayList();
    public List<Worm> getWorms(){ return this.worms; }
    
    public Worm getWorm( int wormNo ){
        assert( worms.size() > wormNo );
        return this.worms.get(wormNo);
    }
    
    
    public List<Flower> getFlowers_orderByDistance(){
        return new ArrayList( this.flowersByDistance );
    }
    
    public List<Ice> getIces_orderByDistance(){
        return new ArrayList( this.icesByDistance );
    }
    
    
    

    // Counters.
    private Counters counters;
    public Counters getCounters() {        return counters;    }
    public void setCounters(Counters counters) {        this.counters = counters;    }
        

    
    public class Counters {    
            private int roundNo;
            private int roundsMax;
            private int flowersRemaining;

            public Counters(int roundNo, int roundsMax, int flowersRemaining) {
                this.roundNo = roundNo;
                this.roundsMax = roundsMax;
                this.flowersRemaining = flowersRemaining;
            }
            
            public int getFlowersRemaining() {        return flowersRemaining;    }
            public void setFlowersRemaining(int flowersRemaining) {        this.flowersRemaining = flowersRemaining;    }
            public int getRoundNo() {        return roundNo;    }
            public void setRoundNo(int roundNo) {        this.roundNo = roundNo;    }
            public int getRoundsMax() {        return roundsMax;    }
            public void setRoundsMax(int roundsMax) {        this.roundsMax = roundsMax;    }
    }
    

    
}// class
