package upcraftlp.mariogame.util;

/**
 * (c)2017 UpcraftLP
 */
public abstract class StoppableThread extends Thread {

    /**
     * how many ticks per second.
     * 0 disables the check.
     */
    protected int ticksPerSecond = 20;
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
        while(!this.shouldStop) { //TODO tick watchdog
            if(this.ticksPerSecond == 0 || SysUtils.getTime() % (1000 / this.ticksPerSecond) == 0) {
                this.runLoop();
            }
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
