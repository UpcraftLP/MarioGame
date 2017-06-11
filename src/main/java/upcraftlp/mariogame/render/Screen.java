package upcraftlp.mariogame.render;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.gui.GuiMainMenu;
import upcraftlp.mariogame.gui.GuiScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * (c)2017 UpcraftLP
 */
public class Screen extends JFrame {

    public Screen(Dimension resolution) {
        this.setName("MarioGame");
        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(resolution);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MarioGame.getGame().shutdown();
            }
        });
        this.setContentPane(new GuiMainMenu());
    }

    private GuiScreen currentScreen;

    public void displayGuiScreen(GuiScreen screen) {
        this.currentScreen = screen;
    }

    /**
     * close the window after shutting down the game
     */
    public void onQuit() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    } //TODO why is this called on another thread??
}
