package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.render.ScreenRenderer;

import javax.swing.*;

/**
 * (c)2017 UpcraftLP
 */
public class GuiMainMenu extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();
        //TODO draw everything: sp, mp, lang, options and quit
        this.addButton(new Button(2, this.width / 2 - 100, this.height / 2, "Singleplayer"));
        this.addButton(new Button(3, this.width/ 2 - 100, this.height / 2 + 30, "Multiplayer"));
        this.addButton(new Button(1, this.width / 2 - 100, this.height / 2 + 60, "Options"));
        this.addButton(new Button(0, this.width / 2 - 100, this.height / 2 + 90, "Quit")); //FIXME caption does not render correctly!
    }

    @Override
    public void actionPerformed(Button button) {
        if(!button.isEnabled()) return;
        switch (button.id) {
            case 0: //quit
                MarioGame.getGame().shutdown();
                break;
            case 1: //options
                log.error("no options page yet!");
                break;
            case 2: //sp
            case 3: //mp
                MarioGame.error("Not implemented yet!");
                break;
        }
    }
}
