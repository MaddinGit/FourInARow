package de.four.in.a.row;

import java.awt.Color;
import javax.swing.JFrame;

/*
 * A class for "four in a row"
 * Two colors represent the players. The colors are red and blue.
 * The class GameBoard extends the class Token.
 * An error occurs if the y - axis is over-flooding of tokens or when a token is thrown aside.
 *
 * TODO: Moebius strip
 * TODO: Combine winner methods
 * TODO: A third player with a different color
 * TODO: Multiplayer
 * TODO: Solver to play against
 *
 * @author Martin Moors
 */
public class GameBoard extends Token {

    public int[][] gbArray = { {} }; // An array representing the GameBoard
    private boolean turn = false; // A variable to change the players turn
    protected MoebiusStripCoordinate ms = null; // The Moebius strip
    private static int winnerCoords[][] = { { 1, 1 }, { 1, 0 }, { 1, -1 },
            { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, 1 }, { 0, -1 } };

    /** Default Constructor */
    public GameBoard() {
    }

    /** Secondary Constructor */
    public GameBoard(int x, int y) {
        this.x = x;
        this.y = y;
        gbArray = new int[x][y];
        fillZeroes();
    }

    /** Set and Get methods */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /** End of Set and Get methods */

    /***************************************************************************************************************/

    /** Fill the array with zeroes */
    public void fillZeroes() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                gbArray[i][j] = 0;
            }
        }
    }

    /*
     * A method to insert a token. Throws an IllegalValueException, if the x -
     * coordinate is moving out of the field.
     *
     * @Param1 position where to insert the token on the x - axis.
     */
    public void insert(int x) throws MyIllegalValueException {
        int freeSpace = 0; // A variable to save the free space on the y - axis

        /** Change token color and begin new turn */
        if (turn) {
            pickBlueToken();
            turn = false;
        } else {
            pickRedToken();
        }

        /** Get a free space on the y - axis */
        for (int y = getY() - 1; y >= 0; y--) {
            if (isFreeSpace(x, y)) {
                freeSpace = y;
            }
        }

        /** Insert token and finish turn. */
        if (isFreeSpace(x, getY() - 1) && isInField(x)) {
            if (isPlayerRed()) {
                insertRedToken(x, freeSpace);
                turn = true;
            } else if (isPlayerBlue()) {
                insertBlueToken(x, freeSpace);
            }
        } else {
            String err = "The y-coordinate is over-flooding!";
            throw new MyIllegalValueException(err);
        }
    }

    /** Insert a blue token at position x and y, where y is a free space. */
    private void insertBlueToken(int x, int freeSpace) {
        gbArray[x][freeSpace] = 2;
    }

    /** Insert a red token at position x and y, where y is a free space. */
    private void insertRedToken(int x, int freeSpace) {
        gbArray[x][freeSpace] = 1;
    }

    /** A method that checks, if a player has four tokens in a row */
    private boolean isWinning(int x) {
        return (x == 4);
    }

    /** Checks if the coordinates x and y are free */
    public boolean isFreeSpace(int x, int y) {
        return (gbArray[x][y] == 0);
    }

    /** A check, if it is the blue players turn. */
    private boolean isPlayerBlue() {
        return (tokenColor == Color.blue);
    }

    /** A check, if it is the red players turn. */
    private boolean isPlayerRed() {
        return (tokenColor == Color.red);
    }

    /** A check if their is a blue token at position x and y */
    public boolean isBlueToken(int x, int y) {
        return (gbArray[x][y] == 2);
    }

    /** A check if their is a red token at position x and y */
    public boolean isRedToken(int x, int y) {
        return (gbArray[x][y] == 1);
    }

    /** A Check if their isn't a following token on the MoebiusStrip */
    private boolean isNotFollowing(int x, int y) {
        return (gbArray[x][y] != gbArray[ms.getX()][ms.getY()]);
    }

    /** A Check if their is a following token on the MoebiusStrip */
    private boolean isFollowing(int x, int y) {
        return (gbArray[x][y] == gbArray[ms.getX()][ms.getY()]);
    }

    /** Checks if the coordinate is in the field */
    private boolean isInField(int x) {
        return (x >= 0 && x < getX());
    }

    /** Pick a red token */
    private void pickRedToken() {
        tokenColor = Color.red;
    }

    /** Pick a blue token */
    private void pickBlueToken() {
        tokenColor = Color.blue;
    }

    /*
     * A winner check if any player has a horizontal, vertical or
     * diagonal row of four tokens on the Moebius strip
     *
     * @return 1 = red, 2 = blue, 0 = no winner
     */
    public int checkForWinnerHorizontal() {
        ms = new MoebiusStripCoordinate();

        for (int x = 0; x < getX(); x++) {
            for (int y = 0; y < getY(); y++) {
                if (x < 5) {
                    int red = 1;
                    int blue = 1;
                    for (int k = 1; k <= 4; k++) {
                        ms.setX(x + k);
                        ms.setY(y);

                        if (isRedToken(x, y)) {
                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }
                        } else if (isBlueToken(x, y)) {
                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                } else {
                    int red = 0;
                    int blue = 0;

                    for (int k = 1; k <= 4; k++) {
                        ms.setX(x + k);
                        ms.setY(y);

                        if (isRedToken(x, y)) {
                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }
                        } else if (isBlueToken(x, y)) {
                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int checkForWinnerVertical() {
        ms = new MoebiusStripCoordinate();

        for (int x = 0; x < getX(); x++) {
            for (int y = 0; y <= 4; y++) {
                if (y < 4) {
                    int red = 1;
                    int blue = 1;

                    for (int k = 1; k <= 4; k++) {
                        ms.setX(x);
                        ms.setY(y + k);

                        if (isRedToken(x, y)) {
                            if (isFollowing(x, y)) {

                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }
                        } else if (isBlueToken(x, y)) {

                            if (isFollowing(x, y)) {

                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                } else {
                    int red = 0;
                    int blue = 0;

                    for (int k = 0; k <= 3; k++) {
                        ms.setX(x);
                        ms.setY(y + k);

                        if (isRedToken(x, y)) {

                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }

                        } else if (isBlueToken(x, y)) {

                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    /** Check for diagonal upwards */
    public int checkForWinnerDiagonalUpwards() {
        ms = new MoebiusStripCoordinate();

        for (int x = 0; x < getX(); x++) {
            for (int y = 0; y <= 4; y++) {
                if (y < 4) {
                    int red = 1;
                    int blue = 1;
                    for (int k = 1; k <= 4; k++) {
                        ms.setX(x + k);
                        ms.setY(y + k);

                        if (isRedToken(x, y)) {
                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }
                        } else if (isBlueToken(x, y)) {
                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                } else {
                    int red = 0;
                    int blue = 0;
                    for (int k = 0; k <= 3; k++) {
                        ms.setX(x + k);
                        ms.setY(y + k);

                        if (isRedToken(x, y)) {

                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }

                        } else if (isBlueToken(x, y)) {

                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int checkForWinnerDiagonalDownwards() {
        ms = new MoebiusStripCoordinate();
        /** Check for diagonal downwards */
        for (int x = 0; x < getX(); x++) {
            for (int y = getY() - 1; y >= 3; y--) {
                if (y >= 3) {
                    int red = 0;
                    int blue = 0;
                    for (int k = 0; k <= 3; k++) {
                        ms.setX(x + k);
                        ms.setY(y - k);

                        if (isRedToken(x, y)) {
                            if (isFollowing(x, y)) {
                                red++;
                                if (isWinning(red)) {
                                    return 1;
                                }
                            } else if (isNotFollowing(x, y)) {
                                red--;
                            }
                        } else if (isBlueToken(x, y)) {
                            if (isFollowing(x, y)) {
                                blue++;
                                if (isWinning(blue)) {
                                    return 2;
                                }
                            } else if (isNotFollowing(x, y)) {
                                blue--;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    /** End of winner check */

    /*********************************************************************************************/

    /** Method toString to print out the GameBoard */
    public String toString() {
        String s = "";
        for (int x = 0; x < getX(); x++) {
            s += "___";
        }
        for (int y = getY() - 1; y >= 0; y--) {
            s += "\n";
            for (int x = 0; x < getX(); x++) {
                s += "|" + gbArray[x][y] + "|";
            }
        }
        s += "\n";
        for (int x = 0; x < getX(); x++) {
            s += "---";
        }
        return s;
    }

    /** Method initialize to create the frame and draw the GameBoard */
    public void initialize() {
        JFrame frame = new JFrame("Four In A Row");
        frame.setLocation(600, 300);
        Gui gui = new Gui();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(348, 380);
        frame.add(gui);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        gb.initialize();
    }
}