package de.four.in.a.row;

import junit.framework.TestCase;

/*
 * Testcases for testing the gameboard
 *
 * @author Martin Moors
 */
public class TestGameBoard extends TestCase {

    public void testThrowIn() {
        GameBoard gb1;
        gb1 = new GameBoard(7, 7);

        gb1.insert(3);
        gb1.insert(3);
        gb1.insert(3);
        gb1.insert(3);
        gb1.insert(3);
        gb1.insert(2);
        gb1.insert(2);
        gb1.insert(1);
        gb1.insert(5);
        gb1.insert(5);

        assertTrue(gb1.gbArray[3][0] == 1);
        assertTrue(gb1.gbArray[3][1] == 2);
        assertTrue(gb1.gbArray[3][2] == 1);
        assertTrue(gb1.gbArray[3][3] == 2);
        assertTrue(gb1.gbArray[3][4] == 1);
        assertTrue(gb1.gbArray[2][0] == 2);
        assertTrue(gb1.gbArray[2][1] == 1);
        assertTrue(gb1.gbArray[1][0] == 2);

        System.out.println("Gameboard - Check:");
        //System.out.println(gb1.toString());

    }

    public void testCheckForWinnerHorizontal() {
        GameBoard gb2;
        gb2 = new GameBoard(8, 8);

        gb2.insert(7);
        gb2.insert(7);
        gb2.insert(6);
        gb2.insert(6);
        gb2.insert(5);
        gb2.insert(5);
        gb2.insert(4);
        gb2.insert(4);
        //gb2.insert(0);

        System.out.println(gb2.toString());

        assertTrue(gb2.checkForWinnerHorizontal() == 1);

    }

    public void testCheckDiagonalDownwards() {
        GameBoard gb3;
        gb3 = new GameBoard(8, 8);

        gb3.insert(0);
        gb3.insert(0);
        gb3.insert(0);
        gb3.insert(0);
        gb3.insert(0);
        gb3.insert(1);
        gb3.insert(1);
        gb3.insert(1);
        gb3.insert(2);
        gb3.insert(2);
        gb3.insert(2);
        gb3.insert(2);
        gb3.insert(2);
        gb3.insert(3);

        System.out.println(gb3.toString());

        assertTrue(gb3.checkForWinnerDiagonal() == 2);

        //System.out.println(gb3.toString());
    }
}
