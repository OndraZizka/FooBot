package cz.oz.redbot.model;

import cz.oz.redbot.model.fo.Ice;
import cz.oz.redbot.model.fo.Flower;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Ondrej Zizka
 */
public class State {

    
    public State(int myWormIndex) {
        this.myWormIndex = myWormIndex;
    }
    
    
    
    // Playground.
    public Playground playground;
    public Playground getPlayground() {        return playground;    }
    public void setPlayground( Playground playground ) {        this.playground = playground;    }
    
    
    private List<Flower> flowers = new LinkedList();
    
    private List<Ice> ices = new LinkedList();
    
    
    private int myWormIndex;
    
    //public Worm myWorm;
    public Worm getMyWorm(){ return this.worms.get( myWormIndex ); }
    
    public List<Worm> worms = new ArrayList();
    public List<Worm> getWorms(){ return this.worms; }
    
    public Worm getWorm( int wormNo ){
        assert( worms.size() > wormNo );
        return this.worms.get(wormNo);
    }

    
    
    
    
    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Ice> getIces() {
        return ices;
    }
    
    
    
    
    /**
     * @returns  A list of flowers sorted by distance, closest first.
     */
    public List<Flower> getFlowers_orderByDistance(){
        // An ugly optimization - 
        // we rely on the list type to flag whether we still build the list or had it sorted.
        if( ! (this.flowers instanceof LinkedList) )
            return this.flowers;
        TreeSet flowersByDistance = new TreeSet( new DistanceComparator( this.getMyWorm().getHead() ) );
        flowersByDistance.addAll( this.flowers );
        return this.flowers = Collections.unmodifiableList( new ArrayList( flowersByDistance ) );
    }
    
    /**
     * @returns  A list of flowers sorted by distance, closest first.
     */
    public List<Ice> getIces_orderByDistance(){
        // An ugly optimization - 
        // we rely on the list type to flag whether we still build the list or had it sorted.
        if( ! (this.ices instanceof LinkedList) )
            return this.ices;
        TreeSet flowersByDistance = new TreeSet( new DistanceComparator( this.getMyWorm().getHead() ) );
        flowersByDistance.addAll( this.ices );
        return this.ices = Collections.unmodifiableList( new ArrayList( flowersByDistance ) );
    }
    
    
    

    // Counters.
    private Counters counters;
    public Counters getCounters() {        return counters;    }
    public void setCounters(Counters counters) {        this.counters = counters;    }

    /**
     * @returns  A list of worms which are not mine.
     */
    public List<Worm> getOtherWorms() {
        List<Worm> worms = new LinkedList(getWorms());
        worms.remove( getMyWorm() );
        return worms;
    }

    /**
     *  Adds a flower. Must not be called after getFlowers_orderBy...() was called.
     */
    public void addFlower( Flower f ) {
        this.flowers.add(f);
    }

    /**
     *  Adds an ice. Must not be called after getIces_orderBy...() was called.
     */
    public void addIce( Ice i ) {
        this.ices.add(i);
    }
        

    /**
     *  Just grouping few properties.
     */
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

