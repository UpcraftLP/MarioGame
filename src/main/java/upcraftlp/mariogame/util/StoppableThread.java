package upcraftlp.mariogame.util;

/**
 * (c)2017 UpcraftLP
 */
public abstract class StoppableThread extends Thread {

    private volatile boolean shouldStop = false;
    private volatile boolean isRunning = false;

    @Override
    public synchronized void start() {
        this.isRunning = true;
        super.start();
    }

    @Override
    public void run() {
        super.run();
        while(!this.shouldStop) {
            this.runLoop();
        }
        this.exit();
    }

    protected synchronized void exit() {
        this.isRunning = false;
    }

    public void shutdown() {
        this.shouldStop = true;
        while(this.isRunning) {
            try {
                this.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void runLoop();
}
