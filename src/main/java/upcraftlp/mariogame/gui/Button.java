package upcraftlp.mariogame.gui;

import javax.swing.*;
import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public class Button extends JButton {

    public final int id;
    private boolean enabled = true;
    private boolean visible = true;

    public Button(int id, int xPos, int yPos, int width, int height, String label) {
        super();
        this.id = id;
        this.setBounds(xPos, yPos, width, height);
        this.setText(label);
    }

    public Button(int id, int xPos, int yPos, String label) {
        this(id, xPos, yPos, 200, 20, label);
    }

    public void setVisible(boolean isVisible) {
        this.visible = isVisible;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void drawButton(int mouseX, int mouseY) {
        if(this.isEnabled()) {
            if(this.isMouseOver(mouseX, mouseY)) {
                Gui.drawModalRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Color.WHITE);
            }
            else Gui.drawModalRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Color.LIGHT_GRAY);
        }
        else {
            Gui.drawModalRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), Color.DARK_GRAY);
        }
        Gui.drawString(this.getText(), this.getX() + this.getWidth() / 2 - Gui.getStringSize(this.getText()) / 2, this.getY() + this.getHeight() / 2 + Gui.getFontHeight()/4, this.isEnabled() ? Color.DARK_GRAY : Color.LIGHT_GRAY);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight();
    }

    @Override
    public void doClick() {
        super.doClick();
    }
}
