package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.util.Constants;

import java.awt.*;
import java.time.Year;

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
        this.addButton(new Button(0, this.width / 2 - 100, this.height / 2 + 90, "Quit"));
        this.addButton(new Button(500, (int) (this.width * 0.88F), (int) (this.height * 0.02F), "Crash"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, int partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawString("MarioGame v" + Constants.VERSION + "\nCopyright (c) 2017-" + Year.now() + " UpcraftLP", this.width / 100, (int) (this.height * 0.97F), Color.BLACK);
    }

    @Override
    public void actionPerformed(Button button) {
        if(!button.isEnabled()) return;
        switch (button.id) {
            case 0: //quit
                MarioGame.getGame().shutdown();
                break;
            case 1: //options
                MarioGame.slide("Not implemented yet!");
                break;
            case 2: //sp
                MarioGame.getGame().displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 3: //mp
                MarioGame.slide("Not implemented yet!");
                break;
            case 500: //crash //TODO remove for version 1.0.0
                MarioGame.getGame().exit(1, new RuntimeException("User requested crash"));
                break;
        }
    }
}
