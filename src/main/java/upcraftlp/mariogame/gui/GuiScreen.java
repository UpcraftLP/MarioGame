package upcraftlp.mariogame.gui;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.util.IKeyListener;
import upcraftlp.mariogame.util.IMouseListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (c)2017 UpcraftLP
 */
public class GuiScreen extends Gui implements IKeyListener, IMouseListener {

    protected List<Button> buttonList = new ArrayList<Button>();

    public void actionPerformed(Button button) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, int partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for(Button button : this.buttonList) {
            if(button.isVisible()) {
                button.drawButton(mouseX, mouseY);
            }
        }

    }

    @Override
    public void initGui() {
        this.buttonList.clear();
    }

    public void addButton(Button button) {
        button.addActionListener(MarioGame.getGame());
        button.addKeyListener(MarioGame.getGame());
        button.addMouseListener(MarioGame.getGame());
        this.buttonList.add(button);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void keyPressed(char typedChar, int keyCode) {

    }

    @Override
    public void keyReleased(char typedChar, int keyCode) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(Button button : this.buttonList) {
            int x = button.getX();
            int y = button.getY();
            if(mouseX > x && mouseX < x + button.getWidth() && mouseY > y && mouseY < y + button.getHeight()) this.actionPerformed(button);
        }
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }
}
