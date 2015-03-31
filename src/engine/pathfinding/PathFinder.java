package engine.pathfinding;

import engine.grid.Gridlike;
import gameobject.GameObject;
import javafx.geometry.Point2D;

/**
 * Responsible for telling GameObjects their next position on a grid.
 * @author Kaighn  
 */
public interface PathFinder {
	/**
	 * Based on a gameobject's current row and column, return its next grid location.
	 */
    public Gridlike getNextLocation(Gridlike cell);
}