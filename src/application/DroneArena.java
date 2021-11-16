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

	private static final long serialVersionUID = -7162694793744161960L;
	List<Drone> drones;
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
		drones = new ArrayList<>();
		for (int i = 0; i < 50; i++)
			addDrone();
	}

	/**
	 * Adds a new drone to the list at a random location.
	 */
	public void addDrone() {
		// if there are more drones than cells, then it will not add a new drone

		// creates a new drone at a random position with a width and height of either 20
		// or 30
		int width = 20 + randomGenerator.nextInt(2) * 10;
		Drone newDrone = new Drone(randomGenerator.nextInt(xSize - width - 2),
				randomGenerator.nextInt(ySize - width - 2), width);

		// makes sure that the position is not already used by a drone, if so
		// it will continue to make generate new coordinates until it has found
		// a space

		for (int i = 0; i < drones.size(); i++) {
			Drone d2 = drones.get(i);
			if (newDrone.hasCollided(d2)) {
				newDrone.setX(randomGenerator.nextInt(xSize - newDrone.getW() - 2));
				newDrone.setY(randomGenerator.nextInt(ySize - newDrone.getW() - 2));
				i = 0;
			}
		}

		// finally adds drone to list
		drones.add(newDrone);

	}

	/**
	 * Will do the animation on a specific drone
	 *
	 * @param d - Drone you wish to animate
	 */
	private void DoAnimation(Drone d) {

		// Check if 2 drones collide
		for (Drone d2 : drones) {
			if (d != d2) {
				if (d.hasCollided(d2)) {
					d.doCollision(d2);
				}
			}
		}

		// Out of bounds check
		if (d.getX() > xSize - d.getW() || d.getX() < 0) {
			// d.setX(xSize - 20);
			d.setxDir(d.getxDir() * -1);
		}

		// Out of bounds check
		if (d.getY() > ySize - d.getW() || d.getY() < 0) {
			// d.setY(ySize - 20);
			d.setyDir(d.getyDir() * -1);
		}

		// Move drone
		d.Move();
	}

	/**
	 * Adds all drones to the canvas.
	 *
	 * @param c - The canvas to which you wish to print the drones.
	 */
	public void showDrones(GraphicsContext gc, Boolean doAnim) {
		for (Drone d : drones) {
			d.DisplayDrone(gc);
			if (doAnim)
				DoAnimation(d);
		}
	}

	/**
	 * returns a string representation of the drone arena. It contains all the
	 * drones, their IDs and their position
	 */
	@Override
	public String toString() {
		String ret = "Arena Size = " + xSize + " x " + ySize + ".\n";

		for (Drone drone : drones) {
			ret += drone.toString() + "\n";
		}

		return ret;
	}

}
