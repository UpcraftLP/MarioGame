package upcraftlp.mariogame.render;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import upcraftlp.mariogame.MarioGame;
import upcraftlp.mariogame.gui.Gui;
import upcraftlp.mariogame.gui.GuiMainMenu;
import upcraftlp.mariogame.util.IKeyListener;
import upcraftlp.mariogame.util.IMouseListener;
import upcraftlp.mariogame.util.StoppableThread;
import upcraftlp.mariogame.util.SysUtils;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * (c)2017 UpcraftLP
 */
public class ScreenRenderer extends StoppableThread implements IMouseListener, IKeyListener {

    private final Logger log = LogManager.getLogger("Render");
    private Screen mainWindow;
    private int partialTicks = 0;
    private final LinkedList<ITickable> renderingFragments = new LinkedList<>();

    @Override
    public synchronized void start() {
        //TODO tick @60FPS
        super.start();
    }

    @Override
    public void run() {
        SysUtils.setThreadname("Render");
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
        if(MarioGame.inGameHasFocus()) {
            this.getMainWindow().getGraphics().clearRect(0, 0, this.getMainWindow().getWidth(), this.getMainWindow().getHeight()); //clear everything so we have a blank canvas
            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            int mouseX = (int) mouseLoc.getX();
            int mouseY = (int) mouseLoc.getY();
            MarioGame.getGame().currentScreen.drawScreen(mouseX, mouseY, this.partialTicks); //TODO partialTicks
            Gui.drawString("", 0, 0); //TODO why is this needed to display all text correctly??!

            //TODO do your ingame rendering here

            synchronized (this.renderingFragments) { //draw slides AFTER everything else
                Iterator<ITickable> iterator = this.renderingFragments.iterator();
                while(iterator.hasNext()) {
                    ITickable tickable = iterator.next();
                    if(tickable.hasExpired()) {
                        iterator.remove();
                        continue;
                    }
                    tickable.tick();
                    tickable.draw(mouseX, mouseY); //TODO call every render tick!
                }
            }
        }
    }

    /**
     * used to add another rendering tick listener
     * be warned that the {@link ITickable} may remain in memory infinitely if it doesn't handle it's expiration!
     */
    public void addTickListener(ITickable tickable) {
        synchronized (this.renderingFragments) {
            this.renderingFragments.add(tickable);
        }
    }

    public Logger getLogger() {
        return this.log;
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
