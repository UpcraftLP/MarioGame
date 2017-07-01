package upcraftlp.mariogame.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.gui.GuiMainMenu;
import upcraftlp.mariogame.util.IKeyListener;
import upcraftlp.mariogame.util.IMouseListener;
import upcraftlp.mariogame.util.StoppableThread;
import upcraftlp.mariogame.util.Util;

import java.awt.*;

/**
 * (c)2017 UpcraftLP
 */
public class ScreenRenderer extends StoppableThread implements IMouseListener, IKeyListener {

    private final Logger log = LogManager.getLogger("Render");
    private Screen mainWindow;
    private boolean isDirty;
    private int ticks = 0;

    public void markDirty() {
        this.isDirty = true;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        Util.setThreadname("Render");
        log.info("initializing render engine...");
        this.mainWindow = new Screen();
        MarioGame.getGame().displayGuiScreen(new GuiMainMenu());
        super.run();
    }

    @Override
    protected void exit() {
        log.info("stopping render engine..");
        //TODO shutdown renderer
        super.exit();
    }

    @Override
    protected void runLoop() {
        if(!MarioGame.getGame().inGameHasFocus) return;
        PointerInfo info = MouseInfo.getPointerInfo();
        Point mouseLoc = info.getLocation();

        //TODO ingame rendering
        if(MarioGame.getGame().currentScreen != null && (this.isDirty || ticks++ % 20 == 0)) {
            this.getMainWindow().getGraphics().clearRect(0, 0, this.getMainWindow().getWidth(), this.getMainWindow().getHeight());
            MarioGame.getGame().currentScreen.drawScreen((int) mouseLoc.getX(), (int) mouseLoc.getY(), 0); //TODO partialTicks
            this.isDirty = false;
        }
    }

    public Screen getMainWindow() {
        return this.mainWindow;
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

    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }
}
