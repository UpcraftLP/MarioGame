package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;

/**
 * (c)2017 UpcraftLP
 */
public class GuiSelectWorld extends GuiScreen {

    private GuiScreen parentScreen;

    public GuiSelectWorld(GuiScreen parent) {
        this.parentScreen = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addButton(new Button(0, this.width - 250, this.height - 50, "Cancel"));
        this.addButton(new Button(1, this.width / 2, this.height - 100, "Play"));

        this.buttonList.get(1).setEnabled(false);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, int partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(Button button) {
        switch (button.id) {
            case 0: //cancel
                MarioGame.getGame().displayGuiScreen(this.parentScreen);

        }
    }
}
