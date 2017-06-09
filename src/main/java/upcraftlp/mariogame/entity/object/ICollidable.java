package upcraftlp.mariogame.entity.object;

import upcraftlp.mariogame.entity.Entity;
import upcraftlp.mariogame.util.math.HitBox;

/**
 * (c)2017 UpcraftLP
 */
public interface ICollidable {

    /**
     * called when an entity collides with this object
     */
    public default void collide(Entity entity) {
        //TODO: reset entity pos and decrese motion
    }

    public HitBox getHitBox();

    public boolean canCollide();

}
