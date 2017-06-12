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

    private List<Button> buttonlist = new ArrayList<Button>();

    public void actionPerformed(Button button) {
        System.out.println("click2");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, int partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for(Button button : this.buttonlist) {
            if(button.isVisible()) {
                if(button.isEnabled()) drawModalRect(button.getX(), button.getY(), button.getWidth(), button.getHeight(), Color.LIGHT_GRAY);
                else drawModalRect(button.getX(), button.getY(), button.getWidth(), button.getHeight(), Color.DARK_GRAY);
                Font f = MarioGame.getGame().getRenderEngine().getMainWindow().getFont();
                drawString(button.getText(), button.getX() + button.getWidth() / 2 - button.getText().length(), button.getY() + button.getHeight() / 2 - f.getSize() / 2, button.isEnabled() ? Color.DARK_GRAY : Color.LIGHT_GRAY);
            }
        }
    }

    public void initGui() {
        //NO-OP
    }

    public void onClose() {
        //NO-OP
    }

    public void addButton(Button button) {
        button.addActionListener(MarioGame.getGame());
        button.addKeyListener(MarioGame.getGame());
        button.addMouseListener(MarioGame.getGame());
        this.buttonlist.add(button);
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
        System.out.println("click23");
        for(Button button : this.buttonlist) {
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
