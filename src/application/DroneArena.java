package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

/**
 * Holds all drones in a list
 *
 * @author joshh
 *
 */
public class DroneArena implements Serializable {

	public List<Object> objects;
	private Random randomGenerator = new Random();
	int xSize, ySize;

	/**
	 * Creates a new drone area of size x, y and will add a single drone to the
	 * arena
	 *
	 * @param x - the length of the arena
	 * @param y - the height of the arena
	 */
	public DroneArena(int x, int y) {
		xSize = x;
		ySize = y;
		objects = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			addDrone();
			addObstacle();
		}
	}

	/**
	 * Reset the Drone Arena back to defaults in case you wish to start again.
	 */
	public void clear() {
		objects.clear();
		Object.lastID = 0;
	}

	/**
	 * Adds a new drone to the list at a random location.
	 */
	public void addDrone() {
		// if there are more drones than cells, then it will not add a new drone

		// creates a new drone at a random position with a width and height of either 20
		// or 30
		int width = randomGenerator.nextInt(2);
		Drone newDrone = null;
		if (width == 0) {
			newDrone = new SmallDrone(randomGenerator.nextInt(xSize - 20), randomGenerator.nextInt(ySize - 20));
		} else if (width == 1) {
			newDrone = new LargeDrone(randomGenerator.nextInt(xSize - 30), randomGenerator.nextInt(ySize - 30));
		}
		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newDrone.hasCollided(d2)) {
				newDrone.setX(randomGenerator.nextInt(xSize - newDrone.getW()));
				newDrone.setY(randomGenerator.nextInt(ySize - newDrone.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newDrone);
	}

	public void addDrone(int x, int y) {
		// if there are more drones than cells, then it will not add a new drone

		// creates a new drone at a random position with a width and height of either 20
		// or 30
		int width = randomGenerator.nextInt(2);
		Drone newDrone = null;
		if (width == 0) {
			newDrone = new SmallDrone(x - 20 / 2, y - 20 / 2);
		} else if (width == 1) {
			newDrone = new LargeDrone(x - 30 / 2, y - 30 / 2);
		}

		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newDrone.hasCollided(d2)) {
				newDrone.setX(randomGenerator.nextInt(xSize - newDrone.getW()));
				newDrone.setY(randomGenerator.nextInt(ySize - newDrone.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newDrone);

	}

	public void addAttackDrone() {
		// if there are more drones than cells, then it will not add a new drone

		// creates a new drone at a random position with a width and height of either 20
		// or 30

		Drone newDrone = null;
		newDrone = new AttackDrone(randomGenerator.nextInt(xSize - 30), randomGenerator.nextInt(ySize - 30));
		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newDrone.hasCollided(d2)) {
				newDrone.setX(randomGenerator.nextInt(xSize - newDrone.getW()));
				newDrone.setY(randomGenerator.nextInt(ySize - newDrone.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newDrone);
	}

	public void addAttackDrone(int x, int y) {
		Drone newDrone = null;
		newDrone = new AttackDrone(x - 30 / 2, y - 30 / 2);

		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newDrone.hasCollided(d2)) {
				newDrone.setX(randomGenerator.nextInt(xSize - newDrone.getW()));
				newDrone.setY(randomGenerator.nextInt(ySize - newDrone.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newDrone);

	}

	public void addObstacle() {
		// TODO Auto-generated method stub
		int width = 20 + randomGenerator.nextInt(2) * 10;
		Obstacle newObstacle = new Obstacle(randomGenerator.nextInt(xSize - width),
				randomGenerator.nextInt(ySize - width), width);

		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newObstacle.hasCollided(d2)) {
				newObstacle.setX(randomGenerator.nextInt(xSize - newObstacle.getW()));
				newObstacle.setY(randomGenerator.nextInt(ySize - newObstacle.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newObstacle);
	}

	public void addObstacle(int x, int y) {
		// TODO Auto-generated method stub
		int width = 20 + randomGenerator.nextInt(2) * 10;
		Obstacle newObstacle = new Obstacle(x - width / 2, y - width / 2, width);

		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < objects.size(); i++) {
			Object d2 = objects.get(i);
			if (newObstacle.hasCollided(d2)) {
				newObstacle.setX(randomGenerator.nextInt(xSize - newObstacle.getW()));
				newObstacle.setY(randomGenerator.nextInt(ySize - newObstacle.getW()));
				i = 0;
			}
		}

		// finally adds drone to list
		objects.add(newObstacle);

	}

	/**
	 * Will do the animation on a specific drone
	 *
	 * @param d - Drone you wish to animate
	 */
	private void DoAnimation(Object d) {
		for (Object d2 : objects) {
			if (d != d2) {
				if (d.hasCollided(d2)) {
					d.doCollision(d2);
				}
			}
		}

		d.Move();

		// Out of bounds check
		if (d.type == "smalldrone" || d.type == "largedrone" || d.type == "attackdrone") {
			// Check if 2 drones collide
			if (d.deadCounter > 20) {
				objects.remove(d);
				return;
			}
			if (d.getX() > xSize - d.getW() || d.getX() < 0) {
				((Drone) d).setxDir(((Drone) d).getxDir() * -1);
			}

			// Out of bounds check
			if (d.getY() > ySize - d.getW() || d.getY() < 0) {
				((Drone) d).setyDir(((Drone) d).getyDir() * -1);
			}
		}

	}

	/**
	 * Adds all drones to the canvas.
	 *
	 * @param gc     - The canvas to which you wish to print the drones.
	 * @param doAnim - true if you want to drones to animate, false if you just wish
	 *               to draw them in their current state
	 */
	public void showDrones(GraphicsContext gc, Boolean doAnim) {
		for (Object d : objects) {
			if (doAnim) {
				DoAnimation(d);
			}
			d.DisplayObject(gc);
		}
	}

	/**
	 * returns a string representation of the drone arena. It contains all the
	 * drones, their IDs and their position
	 */
	@Override
	public String toString() {
		String ret = "Arena Size = " + xSize + " x " + ySize + ".\n";

		for (Object drone : objects) {
			ret += drone.toString() + "\n";
		}

		return ret;
	}

}
