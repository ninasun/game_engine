package engine.gameobject.behaviors;

import engine.gameobject.GameObject;
import gameworld.ObjectCollection;

public class DeathBehavior implements Behavior{

    @Override
    public void execute (ObjectCollection world, GameObject object) {
        object.changeHealth(Integer.MIN_VALUE);//Kills object
    }
}