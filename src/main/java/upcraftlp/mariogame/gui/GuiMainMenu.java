package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;

import javax.swing.*;

/**
 * (c)2017 UpcraftLP
 */
public class GuiMainMenu extends GuiScreen {

    public GuiMainMenu() {
        //TODO draw everything: sp, mp, lang, options and quit
        this.addButton(new Button(0, 100, 200, "Quit"));
    }

    @Override
    public void actionPerformed(Button button) {
        if(!button.isEnabled()) return;
        switch (button.id) {
            case 0: //quit
                MarioGame.getGame().shutdown();
                break;
            case 1: //options
                break;
            case 2: //sp
            case 3: //mp
                //JOptionPane.showMessageDialog(this, "not implemented yet!");
                //TODO showMessage on main screen!
                break;
        }
    }
}
