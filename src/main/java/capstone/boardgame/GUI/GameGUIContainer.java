package capstone.boardgame.GUI;

import capstone.boardgame.GUI.Elements.*;
import capstone.boardgame.GUI.Elements.Button;
import capstone.boardgame.main.Log;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


/**
 * Created by Kyle on 3/14/2016.
 */
public class GameGUIContainer extends BGContainer implements MouseListener {
    private static final String tag = "GameGUIContainer";
    @Override
    public void tick(double deltaTime) {

    }
    @Override
    public void renderComponent(Graphics2D g) {
        g.setFont(BGDrawable.getDefaultfont());
        g.setColor(BGDrawable.getDefaultColor());
        super.renderComponent(g);
    }

    public void addAll(ArrayList<BGDrawable> drawables) {
        for (BGDrawable drawable : drawables) {
            add(drawable);
        }
    }

    private MouseListener checkForButton(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (BGDrawable drawable : drawables) {
            if (drawable instanceof MouseListener) {
                if ((x > drawable.getX() && x < drawable.getX() + drawable.getWidth()) &&
                        (y > drawable.getY() && y < drawable.getY() + drawable.getHeight())) {
                    return (MouseListener)drawable;
                }
            }
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MouseListener ml;
        if ((ml = checkForButton(e)) != null) {
            ml.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MouseListener ml;
        if ((ml = checkForButton(e)) != null) {
            ml.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseListener ml;
        if ((ml = checkForButton(e)) != null) {
            ml.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MouseListener ml;
        if ((ml = checkForButton(e)) != null) {
            ml.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MouseListener ml;
        if ((ml = checkForButton(e)) != null) {
            ml.mouseExited(e);
        }
    }
}
