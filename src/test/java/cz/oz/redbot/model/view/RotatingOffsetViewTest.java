package cz.oz.redbot.model.view;

import static cz.oz.redbot.model.view.RotatingOffsetViewWithOffsetTest.*;

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
public class RotatingOffsetViewTest extends TestCase {
    
    private static final String TEST_MAPS_DIR = "src/test/maps";
    
    
    private State state;
    
    
    public RotatingOffsetViewTest(String testName) {
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
     *  getCell == getCellPush
     */
    public void testGetCell() {
        System.out.println("getCell");
        
        RotatingOffsetView rv;
        
        rv = new RotatingOffsetView( state.getPlayground(), 0 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ));
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 1 );
        System.out.println("UP -> " +  rv.pushCoords(Coords.UP));
        checkWall( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkNull( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 2 );
        checkWall( rv.getCellProjected( Coords.UP ));
        checkNull( rv.getCellProjected( Coords.RIGHT ));
        checkNull( rv.getCellProjected( Coords.DOWN ));
        checkWall( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 3 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkNull( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkWall( rv.getCellProjected( Coords.LEFT ) );
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        rv = new RotatingOffsetView( state.getPlayground(), 4 );
        checkNull( rv.getCellProjected( Coords.UP ));
        checkWall( rv.getCellProjected( Coords.RIGHT ));
        checkWall( rv.getCellProjected( Coords.DOWN ));
        checkNull( rv.getCellProjected( Coords.LEFT ));
        checkWall( rv.getCellProjected( Coords.ZERO ));
        
        /*
        Coords head = state.getMyWorm().getHead();
        int dir = state.getMyWorm().getDirection();
        IView headView = new OffsetView( new RotatingOffsetView( state.getPlayground(), dir ), head );
         */
    }
    
    
    

    /**
     * Test of getRotation method, of class RotatingOffsetView.
     */
    public void testGetRotation() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3 );
        assertEquals( 3, rv.getRotation() );
    }

    public void testGetRotation2() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3 );
        RotatingOffsetView rv2 = new RotatingOffsetView( rv, 2 );
        assertEquals( 1, rv2.getRotation() );

        RotatingOffsetView rv3 = new RotatingOffsetView( rv2, -3 );
        System.out.println("" + (-3 % 4) + ", " + rv2.getRotation() + " - " + rv3);
        assertEquals( 2, rv3.getRotation() );
    }

    /**
     * Test of pullDirection method, of class RotatingOffsetView.
     */
    public void testPullDirection() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3 );
        assertEquals( 2, rv.pullDirection(1) );
    }
    /**
     * Test of pullDirection method, of class RotatingOffsetView.
     */
    public void testPullDirection2() {
        RotatingOffsetView rv = new RotatingOffsetView( state.getPlayground(), 3 );
        RotatingOffsetView rv2 = new RotatingOffsetView( rv, 2 );
        assertEquals( 0, rv2.pullDirection(1) );

        RotatingOffsetView rv3 = new RotatingOffsetView( rv2, -3 );
        assertEquals( 3, rv3.pullDirection(1) );
    }

    /**
     * Test of pullCoords method, of class RotatingOffsetView.
     */
    public void testPullCoords() {
        RotatingOffsetView rv;
        rv = new RotatingOffsetView( state.getPlayground(), 0 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 1 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 2 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 3 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 4 );
        assertEquals( Coords.UP,    rv.pullCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pullCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pullCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pullCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pullCoords( Coords.ZERO ) );
    }

    /**
     * Test of pushCoords method, of class RotatingOffsetView.
     */
    public void testPushCoords() {
        RotatingOffsetView rv;
        rv = new RotatingOffsetView( state.getPlayground(), 0 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 1 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 2 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 3 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
        
        rv = new RotatingOffsetView( state.getPlayground(), 4 );
        assertEquals( Coords.UP,    rv.pushCoords( Coords.UP ) );
        assertEquals( Coords.RIGHT, rv.pushCoords( Coords.RIGHT ) );
        assertEquals( Coords.DOWN,  rv.pushCoords( Coords.DOWN ) );
        assertEquals( Coords.LEFT,  rv.pushCoords( Coords.LEFT ) );
        assertEquals( Coords.ZERO,  rv.pushCoords( Coords.ZERO ) );
    }
}
