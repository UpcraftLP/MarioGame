package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * (c)2017 UpcraftLP
 */
public class Screen extends JFrame {

    public Screen() {
        this.setName("MarioGame");
        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(this.getMaximumSize());
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
