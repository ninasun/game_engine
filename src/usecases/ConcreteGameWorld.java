package usecases;

import engine.grid.Grid;
import engine.pathfinding.PathFinder;
import gameobject.Enemy;
import gameobject.GameObject;
import gameobject.Tower;
import gameworld.GameWorld;


public class ConcreteGameWorld implements GameWorld {

    private Grid grid;
    private PathFinder pathFinder;

    @Override
    // USE CASE 1: a tower detects enemy in range... fires projectile
    // USE CASE 3: an enemy follows its path
    public void updateGameObjects () {
        // iterate through all gameObjects and update, in turn, their
        // position, physics and graphics.

        // CASE 1: tower
        Tower concreteTower =
                new ConcreteTower(new TowerMovementComponent(),
                                  new ConcretePhysicsComponent(grid.getCollisionEngine()),
                                  new ConcreteGraphicsComponent());
        // now updating its physics
        concreteTower.updatePhysics();

        // CASE 3: enemy
        Enemy concreteEnemy =
                new ConcreteEnemy(new EnemyMovementComponent(pathFinder),
                                  new ConcretePhysicsComponent(grid.getCollisionEngine()),
                                  new ConcreteGraphicsComponent());
        // now updating its movement
        concreteEnemy.updatePosition();
    }

    @Override
    public PathFinder getPathFinder () {
        return pathFinder;
    }

    @Override
    public void addObject (GameObject object) {
        // TODO Auto-generated method stub

    }

}
