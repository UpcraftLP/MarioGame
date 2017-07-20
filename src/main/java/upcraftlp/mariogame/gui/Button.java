package upcraftlp.mariogame.gui;

import javax.swing.*;

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

    @Override
    public void doClick() {
        super.doClick();
    }
}
