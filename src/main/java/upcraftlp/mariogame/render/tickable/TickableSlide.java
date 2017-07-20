package upcraftlp.mariogame.render.tickable;

import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.gui.Gui;
import upcraftlp.mariogame.render.ITickable;
import upcraftlp.mariogame.util.StringUtils;

import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public class TickableSlide implements ITickable {

    public TickableSlide(String text) {
        this.x = MarioGame.getGame().getRenderEngine().getMainWindow().getWidth();
        this.y = 0;
        this.width = 300;
        this.height = 100;
        this.text = text;
    }

    public TickableSlide setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    protected int ticksExisted = 0;
    protected int width,height,x,y;
    private Color color = Color.CYAN;
    private Color textColor = Color.WHITE;

    /**
     * the desired lifespan in ticks
     */
    protected int lifespan = 100;
    protected String text;

    @Override
    public void tick() {
        if(ticksExisted < lifespan / 5) {
            this.x -= this.width / 20; //move in
        }
        else if(ticksExisted < lifespan * 0.8F) {
            //just stay there
        }
        else {
            this.x += this.width / 20; //move out
        }
        this.ticksExisted++;
    }

    @Override
    public boolean hasExpired() {
        return this.ticksExisted > this.lifespan;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Gui.drawModalRect(this.x, this.y, this.width, this.height, this.color);
        if(!StringUtils.isNullOrEmpty(this.text)) {
            Gui.drawString(this.text, this.x + this.width / 5, this.y + this.height / 3, this.textColor);
            //TODO BIG FONTSIZE, TEXT SHADOW
        }


    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
