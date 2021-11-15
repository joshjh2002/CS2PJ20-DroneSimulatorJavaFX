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
		// for (int i = 0; i < 10; i++)
		addDrone();
	}

	/**
	 * Adds a new drone to the list at a random location.
	 */
	public void addDrone() {
		// if there are more drones than cells, then it will not add a new drone
		if (drones.size() < xSize * ySize) {
			// creates a new drone at a random position
			Drone newDrone = new Drone(randomGenerator.nextInt(xSize - 20), randomGenerator.nextInt(ySize - 20));

			// makes sure that the position is not already used by a drone, if so
			// it will continue to make generate new coordinates until it has found
			// a space

			for (int i = 0; i < drones.size(); i++) {
				Drone d2 = drones.get(i);

				if (newDrone.getX() < d2.getX() + 20 && newDrone.getX() + 20 > d2.getX()
						&& newDrone.getY() < d2.getY() + 20 && 20 + newDrone.getY() > d2.getY()) {
					newDrone.setX(randomGenerator.nextInt(xSize));
					newDrone.setY(randomGenerator.nextInt(ySize));
					i = 0;
				}

			}

			// finally adds drone to list
			drones.add(newDrone);

		}

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
				if (d.getX() < d2.getX() + 20 && d.getX() + 20 > d2.getX() && d.getY() < d2.getY() + 20
						&& 20 + d.getY() > d2.getY()) {
					d.doCollision(d2);
				}
			}
		}

		// Out of bounds check
		if (d.getX() > xSize - 20 || d.getX() < 0) {
			// d.setX(xSize - 20);
			d.setxDir(d.getxDir() * -1);
		}

		// Out of bounds check
		if (d.getY() > ySize - 20 || d.getY() < 0) {
			// d.setY(ySize - 20);
			d.setyDir(d.getyDir() * -1);
		}

		// Move drone
		d.Move();
	}

	/**
	 * Will cycle through all drones currently in the list and return the drone that
	 * is at that position.
	 * 
	 * @param x position at which wish to check for drone
	 * @param y position at which wish to check for drone
	 * @return The drone at position (x, y). Null if there is no drone.
	 */
	public Drone getDroneAt(int x, int y) {
		for (Drone drone : drones) {
			if (drone.isHere(x, y)) {
				return drone;
			}
		}
		return null;
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
