package capstone.boardgame.GUI.Elements;

import java.awt.*;

/**
 * Created by Kyle on 3/8/2016.
 */
public class Label extends BGDrawable {
    private static final String tag = "Label";
    protected String text;
    public Label(int x, int y, String text) {
        super(x, y, 0, 0);
        this.text = text;
    }

    @Override
    public void tick(double deltaTime) {

    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void renderComponent(Graphics2D g) {
        int fontSize = getFont().getSize();
        int yOff = (int)(fontSize*.75);

        g.drawString(text, x, y + yOff);
    }
}
