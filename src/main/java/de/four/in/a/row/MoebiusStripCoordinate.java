package de.four.in.a.row;

/*
 * This class is a Moebius strip coordinate
 *
 * @author Martin Moors
 */
public class MoebiusStripCoordinate extends Coordinate {

    GameBoard gb = new GameBoard(8, 8);

    /** Default constructor */
    public MoebiusStripCoordinate() {
    }

    /** Secondary constructor */
    public MoebiusStripCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Set and Get methods, does imply the moebius strip */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        if (isInField(x)) {
            this.x = x;
        } else if (isRightStrip(x)) {
            this.x = glueRight(x);
            //this.y = invertY();
        } else if (isLeftStrip(x)) {
            this.x = glueLeft(x);
            //this.y = invertY();
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    /** End of Set and Get methods */

    /***************************************************************************************************************/

    /** Invert the y coord */
    private int invertY() {
        return ((gb.getY() - 1) - getY());
    }

    /** Glues the right side of the x - axis together */
    private int glueRight(int x) {
        return ((x % gb.getX()-1));

    }

    /** Glues the left side of the x - axis together */
    private int glueLeft(int x) {
        return ((x % gb.getX()) + gb.getX());
    }

    /** Checks if x is a left strip */
    private boolean isLeftStrip(int x) {
        return (x < 0);
    }

    /** Checks if x is a right strip */
    private boolean isRightStrip(int x) {
        return (x > gb.getX());
    }

    /** Checks if x is in the field */
    private boolean isInField(int x) {
        return ((x >= 0) && (x < gb.getX()));
    }

    /** Move Coordinate */
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

}
