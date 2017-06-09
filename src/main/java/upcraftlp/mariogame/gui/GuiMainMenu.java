package upcraftlp.mariogame.gui;

import javax.swing.*;

/**
 * (c)2017 UpcraftLP
 */
public class GuiMainMenu extends GuiScreen {

    public GuiMainMenu() {
        //TODO draw everything: sp, mp, lang, options and quit
        this.add(new JButton() { //FIXME! //TODO position
            @Override
            public void doClick() {
                super.doClick();
                System.out.println("click");
            }
        });
    }
}
