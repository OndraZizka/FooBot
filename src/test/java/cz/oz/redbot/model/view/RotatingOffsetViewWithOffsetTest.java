/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.oz.redbot.model.view;

import cz.oz.redbot.RedBotApp;
import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.fo.FieldObject;
import cz.oz.redbot.model.fo.Flower;
import cz.oz.redbot.model.fo.Ice;
import cz.oz.redbot.model.fo.Wall;
import junit.framework.TestCase;

/**
 *
 * @author ondra
 */
public class RotatingOffsetViewWithOffsetTest extends TestCase {
    
    private static final String TEST_MAPS_DIR = "src/test/maps";
    
    
    private State state;
    
    
    public RotatingOffsetViewWithOffsetTest(String testName) {
        super(testName);
    }
    
    
    
    @Override
    protected void setUp() throws Exception {
        this.state = RedBotApp.loadStateFromFile( TEST_MAPS_DIR + "/ViewsTest.txt", 3 );
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    

    /**
     * Test of getCell method, of class RotatingOffsetView.
     * 
     *   0 2
     *  0#########################################
     *  1#12*456789123456789123456789123456789123#
     *   #123456789123456789123456789123456789123#
     */
    public void testGetCell() {
        System.out.println("getCell");
        
        RotatingOffsetView rv;
        
        rv = new RotatingOffsetView( state.getPlayground(), 0, new Coords(3, 1) );
        checkWall( rv.getCellProjected( Coords.UP ));
        checkFlower4( rv.getCellProjected( Coords.RIGHT ));
        checkFlower3( rv.getCellProjected( Coords.DOWN ));
        checkFlower2( rv.getCellProjected( Coords.LEFT ));
        checkIce( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 1, new Coords(3, 1) );
        System.out.println("UP -> " +  rv.pushCoords(Coords.UP));
        checkFlower4( rv.getCellProjected( Coords.UP ));
        checkFlower3( rv.getCellProjected( Coords.RIGHT ));
        checkFlower2( rv.getCellProjected( Coords.DOWN ));
        checkWall( rv.getCellProjected( Coords.LEFT ));
        checkIce( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 2, new Coords(3, 1) );
        checkFlower3( rv.getCellProjected( Coords.UP ));
        checkFlower2( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkFlower4( rv.getCellProjected( Coords.LEFT ));
        checkIce( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 3, new Coords(3, 1) );
        checkFlower2( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkFlower4( rv.getCellProjected( Coords.DOWN ));
        checkFlower3( rv.getCellProjected( Coords.LEFT ));
        checkIce( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 4, new Coords(3, 1) );
        checkWall( rv.getCellProjected( Coords.UP ));
        checkFlower4( rv.getCellProjected( Coords.RIGHT ));
        checkFlower3( rv.getCellProjected( Coords.DOWN ));
        checkFlower2( rv.getCellProjected( Coords.LEFT ));
        checkIce( rv.getCellProjected( Coords.ZERO ));
    }
    
    static void checkNull( FieldObject fo ){
        System.out.println("Checking for null at " + fo);
        assertNull( fo );
    }
    static void checkWall( FieldObject fo ){
        System.out.println("Checking for Wall at " + fo);
        assertTrue( fo instanceof Wall );
    }
    static void checkIce( FieldObject fo ){
        System.out.println("Checking for Ice at " + fo);
        assertTrue( fo instanceof Ice );
    }
    static void checkFlower( FieldObject fo ){ checkFlower(fo, 0); }
    static void checkFlower( FieldObject fo, int points ){
        System.out.println("Checking for Flower("+( points != 0 ? points : "*" )+") at " + fo);
        assertTrue( fo instanceof Flower );
        if( points != 0 )
            assertEquals( points, ((Flower)fo).getPoints() );
    }
    static void checkFlower2( FieldObject fo ){ checkFlower(fo, 2); }
    static void checkFlower3( FieldObject fo ){ checkFlower(fo, 3); }
    static void checkFlower4( FieldObject fo ){ checkFlower(fo, 4); }
    
    

    /**
     * Test of getRotation method, of class RotatingOffsetView.
     */
    public void testGetRotation() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3, new Coords(3, 1) );
        assertEquals( 3, rv.getRotation() );
    }

    public void testGetRotation2() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3, new Coords(3, 1) );
        RotatingOffsetView rv2 = new RotatingOffsetView( rv, 2, new Coords(3, 1) );
        assertEquals( 1, rv2.getRotation() );

        RotatingOffsetView rv3 = new RotatingOffsetView( rv2, -3, new Coords(3, 1) );
        System.out.println("" + (-3 % 4) + ", " + rv2.getRotation() + " - " + rv3);
        assertEquals( 2, rv3.getRotation() );
    }

    /**
     * Test of pullDirection method, of class RotatingOffsetView.
     */
    public void testPullDirection() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3, new Coords(3, 1) );
        assertEquals( 2, rv.pullDirection(1) );
    }
    /**
     * Test of pullDirection method, of class RotatingOffsetView.
     */
    public void testPullDirection2() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3, new Coords(3, 1) );
        RotatingOffsetView rv2 = new RotatingOffsetView( rv, 2, new Coords(3, 1) );
        assertEquals( 0, rv2.pullDirection(1) );

        RotatingOffsetView rv3 = new RotatingOffsetView( rv2, -3, new Coords(3, 1) );
        assertEquals( 3, rv3.pullDirection(1) );
    }

    /**
     * Test of pullCoords method, of class RotatingOffsetView.
     * 
     *   0  3
     *  0#########################################
     *  1#12*456789123456789123456789123456789123#
     *   #123456789123456789123456789123456789123#
     */
    public void testPullCoords() {
        RotatingOffsetView rv;
        Coords off = new Coords(3, 1);
        
        // We're pulling cells around 0,0 of the playground.
        
        rv = new RotatingOffsetView( state.getPlayground(), 0, off );
        assertEquals( off.invert().add(Coords.UP),    rv.pullCoords( Coords.UP ) );
        assertEquals( off.invert().add(Coords.RIGHT), rv.pullCoords( Coords.RIGHT ) );
        assertEquals( off.invert().add(Coords.DOWN),  rv.pullCoords( Coords.DOWN ) );
        assertEquals( off.invert().add(Coords.LEFT),  rv.pullCoords( Coords.LEFT ) );
        assertEquals( off.invert().add(Coords.ZERO),  rv.pullCoords( Coords.ZERO ) );
        
        // TODO: This is screwed up, put it manually.
        rv = new RotatingOffsetView( state.getPlayground(), 1, off );
        assertEquals( new Coords(-1, 2),  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 0, 3),  rv.pullCoords( Coords.DOWN ) );
        assertEquals( new Coords(-1, 4),  rv.pullCoords( Coords.LEFT ) );
        assertEquals( new Coords(-2, 3),  rv.pullCoords( Coords.UP ) );
        assertEquals( new Coords(-1, 3),  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 2, off );
        assertEquals( new Coords( 2, 1),  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 3, 0),  rv.pullCoords( Coords.DOWN ) );
        assertEquals( new Coords( 4, 1),  rv.pullCoords( Coords.LEFT ) );
        assertEquals( new Coords( 3, 2),  rv.pullCoords( Coords.UP ) );
        assertEquals( new Coords( 3, 1),  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 3, off );
        assertEquals( new Coords( 1,-2),  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 0,-3),  rv.pullCoords( Coords.DOWN ) );
        assertEquals( new Coords( 1,-4),  rv.pullCoords( Coords.LEFT ) );
        assertEquals( new Coords( 2,-3),  rv.pullCoords( Coords.UP ) );
        assertEquals( new Coords( 1,-3),  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 4, off );
        assertEquals( off.invert().add(Coords.UP),    rv.pullCoords( Coords.UP ) );
        assertEquals( off.invert().add(Coords.RIGHT), rv.pullCoords( Coords.RIGHT ) );
        assertEquals( off.invert().add(Coords.DOWN),  rv.pullCoords( Coords.DOWN ) );
        assertEquals( off.invert().add(Coords.LEFT),  rv.pullCoords( Coords.LEFT ) );
        assertEquals( off.invert().add(Coords.ZERO),  rv.pullCoords( Coords.ZERO ) );
    }

    /**
     * Test of pushCoords method, of class RotatingOffsetView.
     */
    public void testPushCoords() {
        RotatingOffsetView rv;
        Coords off = new Coords(3, 1);
        
        rv = new RotatingOffsetView( state.getPlayground(), 0, off );
        assertEquals( off.add(Coords.UP),    rv.pushCoords( Coords.UP ) );
        assertEquals( off.add(Coords.RIGHT), rv.pushCoords( Coords.RIGHT ) );
        assertEquals( off.add(Coords.DOWN),  rv.pushCoords( Coords.DOWN ) );
        assertEquals( off.add(Coords.LEFT),  rv.pushCoords( Coords.LEFT ) );
        assertEquals( off.add(Coords.ZERO),  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 1, off );
        assertEquals( new Coords( 3, 2),  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 2, 1),  rv.pushCoords( Coords.DOWN ) );
        assertEquals( new Coords( 3, 0),  rv.pushCoords( Coords.LEFT ) );
        assertEquals( new Coords( 4, 1),  rv.pushCoords( Coords.UP ) );
        assertEquals( new Coords( 3, 1),  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 2, off );
        assertEquals( new Coords( 2, 1),  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 3, 0),  rv.pushCoords( Coords.DOWN ) );
        assertEquals( new Coords( 4, 1),  rv.pushCoords( Coords.LEFT ) );
        assertEquals( new Coords( 3, 2),  rv.pushCoords( Coords.UP ) );
        assertEquals( new Coords( 3, 1),  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 3, off );
        assertEquals( new Coords( 3, 2),    rv.pushCoords( Coords.LEFT ) );
        assertEquals( new Coords( 2, 1), rv.pushCoords( Coords.UP ) );
        assertEquals( new Coords( 3, 0),  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( new Coords( 4, 1),  rv.pushCoords( Coords.DOWN ) );
        assertEquals( new Coords( 3, 1),  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 4, off );
        assertEquals( off.add(Coords.UP),    rv.pushCoords( Coords.UP ) );
        assertEquals( off.add(Coords.RIGHT), rv.pushCoords( Coords.RIGHT ) );
        assertEquals( off.add(Coords.DOWN),  rv.pushCoords( Coords.DOWN ) );
        assertEquals( off.add(Coords.LEFT),  rv.pushCoords( Coords.LEFT ) );
        assertEquals( new Coords( 3, 1),  rv.pushCoords( Coords.ZERO ) );
    }
}
