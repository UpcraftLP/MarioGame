package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.render.Screen;

import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public class Gui {

    private static final Color BLACK_ALPHA = new Color(0, 0, 0, 10);
    private int width, height;
    private final Screen DELEGATE = MarioGame.getGame().getRenderEngine().getMainWindow();

    public Gui() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = dimension.width;
        this.height = dimension.height;
    }

    /**
     * draw the gui onto the screen
     */
    public void drawScreen(int mouseX, int mouseY, int partialTicks) {
        this.drawBackground(mouseX, mouseY, partialTicks);

    }

    public void drawBackground(int mouseX, int mouseY, int partialTicks) {
        this.drawDefaultBackground();
    }

    public void drawDefaultBackground() {
        this.drawModalRect(0, 0, this.width, this.height, BLACK_ALPHA);
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setColor(Color c) {
        DELEGATE.getGraphics().setColor(c);
    }

    public Color getColor() {
        return DELEGATE.getGraphics().getColor();
    }

    public void drawModalRect(int x, int y, int width, int height, Color c) {
        Graphics g = DELEGATE.getLayeredPane().getGraphics();
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }

    public void drawString(String text, int x, int y, Color c) {

        Graphics g = DELEGATE.getGlassPane().getGraphics();
        g.setColor(c);
        g.drawString(text, x, y + g.getFont().getSize());
    }

    public void drawString(String text, int x, int y) {
        drawString(text, x, y, Color.WHITE);
    }

}
