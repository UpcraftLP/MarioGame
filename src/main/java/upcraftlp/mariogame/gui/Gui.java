package upcraftlp.mariogame.gui;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.render.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public abstract class Gui {

    protected static final Color BLACK_ALPHA = new Color(0, 0, 0, 10);
    protected int width, height;
    private static final Screen DELEGATE = MarioGame.getGame().getRenderEngine().getMainWindow(); //TODO what the hell did I do here??
    protected final Logger log = MarioGame.getGame().getRenderEngine().getLogger();

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

    protected void drawBackground(int mouseX, int mouseY, int partialTicks) {
        this.drawDefaultBackground();
    }

    protected void drawDefaultBackground() {
        this.drawModalRect(0, 0, this.width, this.height, BLACK_ALPHA);
    }

    /**
     * used to add all GUI elements when GUI is created or resized
     */
    public abstract void initGui();

    /**
     * called when a GUI is closed to clear all keystrokes
     */
    public void onClose() {
        //TODO release all keys!
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        this.initGui();
    }

    public void setColor(Color c) {
        DELEGATE.getGraphics().setColor(c);
    }

    public Color getColor() {
        return DELEGATE.getGraphics().getColor();
    }

    public static void drawModalRect(int x, int y, int width, int height, Color c) {
        Graphics g = DELEGATE.getContentPane().getGraphics();
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }

    /**
     * draw a single strings or multiple lines, separated by {@code \n}
     */
    public static void drawString(String text, int x, int y, Color c) {
        Graphics g = DELEGATE.getGlassPane().getGraphics();
        g.setColor(c);

        String[]split = text.split("\n");
        for (int i = 0; i < split.length; i++) {
            g.drawString(split[i], x, y + getFontHeight() * i);
        }

    }

    public static void drawString(String text, int x, int y) {
        drawString(text, x, y, Color.WHITE);
    }

    public static int getStringSize(String string) {
        Graphics g = DELEGATE.getGlassPane().getGraphics();
        return g.getFontMetrics(g.getFont()).stringWidth(string);
    }

    public static int getFontHeight() {
        Graphics g = DELEGATE.getGlassPane().getGraphics();
        return g.getFontMetrics(g.getFont()).getHeight();
    }

}
