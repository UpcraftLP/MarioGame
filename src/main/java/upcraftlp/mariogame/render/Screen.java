package upcraftlp.mariogame.render;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.gui.*;
import upcraftlp.mariogame.gui.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * (c)2017 UpcraftLP
 */
public class Screen extends JFrame {

    public Screen() {
        this.setName("MarioGame");
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MarioGame.getGame().shutdown();
            }
        });
        this.setVisible(true);
        this.setEnabled(true);
        this.addKeyListener(MarioGame.getGame());
        this.addMouseListener(MarioGame.getGame());
        this.getGlassPane().setVisible(true); //FIXME render screen in foreground layer!
    }

    @Override
    public void setSize(int width, int height) {
        if(MarioGame.getGame().currentScreen != null) MarioGame.getGame().currentScreen.setDimensions(width, height);
        super.setSize(width, height);
    }
}
