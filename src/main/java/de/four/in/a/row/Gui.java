package de.four.in.a.row;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * A Class to draw a GUI for Four In a Row
 *
 * @author Martin Moors
 */
class Gui extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    GameBoard gb = new GameBoard(8, 8);
    Point point = new Point(); // Variable for clicked point
    public int[] mapFieldX = { 0, 40, 80, 120, 160, 200, 240, 280 };
    public int[] mapFieldY = { 280, 240, 200, 160, 120, 80, 40, 0 };

    public Gui() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point = e.getPoint();

                gb.insert(getFieldPos());
                repaint();

                if (gb.checkForWinnerVertical() == 1  || gb.checkForWinnerHorizontal() == 1 || gb.checkForWinnerDiagonal() == 1) {
                    JOptionPane.showMessageDialog(null, "Red Wins!");
                    gb.fillZeroes();
                }
                if (gb.checkForWinnerVertical() == 2 || gb.checkForWinnerHorizontal() == 2 || gb.checkForWinnerDiagonal() == 2) {

                    JOptionPane.showMessageDialog(null, "Blue Wins!");
                    gb.fillZeroes();
                }

                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw lines vertically
        for (int i = 0; i <= 330; i += 40) {
            Line2D line = new Line2D.Double(10 + i, 10, 10 + i, 330);
            g2d.draw(line);
        }

        // Draw lines horizontally
        for (int i = 0; i <= 330; i += 40) {
            Line2D line = new Line2D.Double(10, 10 + i, 330, 10 + i);
            g2d.draw(line);
        }

        // Draw tokens
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (gb.isBlueToken(x, y)) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawOval(10 + mapFieldX[x], 10 + mapFieldY[y], 40, 40);
                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(10 + mapFieldX[x], 10 + mapFieldY[y], 40, 40);
                } else if (gb.isRedToken(x, y)) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawOval(10 + mapFieldX[x], 10 + mapFieldY[y], 40, 40);
                    g2d.setColor(Color.RED);
                    g2d.fillOval(10 + mapFieldX[x], 10 + mapFieldY[y], 40, 40);
                }
            }
        }
    }

    /*
     * Method isInField to get the position the user has clicked
     *
     * @return clicked position on the x-axis
     */
    private int getFieldPos() {
        if ((point.getX() > 10 && point.getX() <= 50)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 0;
        } else if ((point.getX() > 50 && point.getX() <= 90)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 1;
        } else if ((point.getX() > 91 && point.getX() <= 130)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 2;
        } else if ((point.getX() > 130 && point.getX() <= 160)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 3;
        } else if ((point.getX() > 160 && point.getX() <= 200)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 4;
        } else if ((point.getX() > 200 && point.getX() <= 240)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 5;
        } else if ((point.getX() > 240 && point.getX() <= 280)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 6;
        } else if ((point.getX() > 280 && point.getX() <= 320)
                && (point.getY() > 10 && point.getY() < 330)) {
            return 7;
        }
        return -1;
    }


    public void actionPerformed(ActionEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
