package upcraftlp.mariogame.gui;

import javax.swing.*;

/**
 * (c)2017 UpcraftLP
 */
public class Gui extends JPanel {

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
        //TODO: background
    }
}
