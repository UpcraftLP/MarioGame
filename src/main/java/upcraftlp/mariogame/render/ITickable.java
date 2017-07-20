package upcraftlp.mariogame.render;

/**
 * (c)2017 UpcraftLP
 */
public interface ITickable {

    /**
     * called each tick to update the element
     */
    public void tick();

    /**
     * when this method returns true the object will no longer be kept in memory
     */
    public boolean hasExpired();

    public void draw(int mouseX, int mouseY);

}
