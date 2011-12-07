/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.oz.redbot.model.view;

import cz.oz.redbot.RedBotApp;
import cz.oz.redbot.model.Coords;
import cz.oz.redbot.model.State;
import cz.oz.redbot.model.fo.FieldObject;
import cz.oz.redbot.model.fo.Wall;
import junit.framework.TestCase;

/**
 *
 * @author ondra
 */
public class RotatingViewTest extends TestCase {
    
    private static final String TEST_RESOURCES_DIR = "src/test/resources";
    
    
    private State state;
    
    
    public RotatingViewTest(String testName) {
        super(testName);
    }
    
    
    
    @Override
    protected void setUp() throws Exception {
        this.state = RedBotApp.loadStateFromFile( TEST_RESOURCES_DIR + "/ViewsTest.txt", 3 );
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    

    /**
     * Test of getCell method, of class RotatingView.
     *  getCell == getCellPush
     */
    public void testGetCell() {
        System.out.println("getCell");
        
        RotatingView rv;
        
        rv = new RotatingView( state.getPlayground(), 0 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ));
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingView( state.getPlayground(), 1 );
        checkWall( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkNull( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingView( state.getPlayground(), 2 );
        checkWall( rv.getCellProjected( Coords.UP ));
        checkNull( rv.getCellProjected( Coords.RIGHT ));
        checkNull( rv.getCellProjected( Coords.DOWN ));
        checkWall( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingView( state.getPlayground(), 3 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkNull( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkWall( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingView( state.getPlayground(), 4 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkNull( rv.getCellProjected( Coords.RIGHT ));
        checkNull( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        /*
        Coords head = state.getMyWorm().getHead();
        int dir = state.getMyWorm().getDirection();
        IView headView = new OffsetView( new RotatingView( state.getPlayground(), dir ), head );
         */
    }
    
    private void checkNull( FieldObject fo ){ assertNull( fo ); }
    private void checkWall( FieldObject fo ){ assertTrue( fo instanceof Wall ); }
    
    

    /**
     * Test of getRotation method, of class RotatingView.
     */
    public void testGetRotation() {
        System.out.println("getRotation");
        RotatingView instance = null;
        int expResult = 0;
        int result = instance.getRotation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullDirection method, of class RotatingView.
     */
    public void testPullDirection() {
        System.out.println("pullDirection");
        int dir = 0;
        RotatingView instance = null;
        int expResult = 0;
        int result = instance.pullDirection(dir);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullCoords method, of class RotatingView.
     */
    public void testPullCoords() {
        RotatingView rv;
        rv = new RotatingView( state.getPlayground(), 0 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 1 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 2 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 3 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 4 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
    }

    /**
     * Test of pushCoords method, of class RotatingView.
     */
    public void testPushCoords() {
        RotatingView rv;
        rv = new RotatingView( state.getPlayground(), 0 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 1 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 2 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 3 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingView( state.getPlayground(), 4 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
    }
}
