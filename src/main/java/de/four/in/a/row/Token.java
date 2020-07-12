package de.four.in.a.row;

import java.awt.Color;

/*
 * A token's coordinate and its color
 *
 * @author Martin Moors
 */
public class Token {

    protected int x;
    protected int y;
    protected Color tokenColor = Color.red; // Red begins

    /** Primary constructor */
    public Token() {
        super();
    }

    /** Set and Get methods */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getCol() {
        return tokenColor;
    }

    public void setCol(Color col) {
        this.tokenColor = col;
    }
    /** End of Set and Get methods */
}