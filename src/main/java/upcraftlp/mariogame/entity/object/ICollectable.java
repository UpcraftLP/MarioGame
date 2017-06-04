package upcraftlp.mariogame.entity.object;

import upcraftlp.mariogame.entity.Entity;

/**
 * (c)2017 UpcraftLP
 */
public interface ICollectable {

    /**
     * called when an entity tries to collect the object
     */
    public void onCollect(Entity entity);
}
