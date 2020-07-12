package de.four.in.a.row;

import junit.framework.TestCase;

 /** Testcases for the möbius class
 *
 * @author Martin Moors
 */

public class TestMoebiusStripCoordinate extends TestCase {

    MoebiusStripCoordinate msc; // The Coordinate

// Test Constructor

    public void testConstructor() {
        MoebiusStripCoordinate test = new MoebiusStripCoordinate();
        assertNotNull(test);
    }

// Test move for the coords (2,2)

    public void testMove() {
        msc = new MoebiusStripCoordinate(2, 2);
        msc.move(4, 0);
        assertTrue(msc.getX() == 1);
        assertTrue(msc.getY() == 3);
        msc.move(-1, 0);
        assertTrue(msc.getX() == 0);
        assertTrue(msc.getY() == 3);
        msc.move(-2, 0);
        assertTrue(msc.getX() == 3);
        assertTrue(msc.getY() == 2);
        msc.move(-1, 2);
        assertTrue(msc.getX() == 2);
        assertTrue(msc.getY() == 4);
    }
}
