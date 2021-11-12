package application;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class used to instantiate a drone
 * 
 * @author joshh
 *
 */
public class Drone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9159655539969594695L;
	static int lastID = 0;
	private int x, y, id, xDir = 0, yDir = 0;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param x   coordinate at which you wish to place a drone
	 * @param y   coordinate at which you wish to place a drone
	 * @param dir in which you wish to make the drone move
	 */
	public Drone(int x, int y) {
		this.x = x;
		this.y = y;
		// lastID++;
		id = ++lastID;
		Random rnd = new Random();

		while (xDir == 0 && yDir == 0) {
			xDir = rnd.nextInt(3) - 1;
			yDir = rnd.nextInt(3) - 1;
		}
	}

	/**
	 * Will draw the drone to the canvas and rotate the drone clockwise
	 * 
	 * @param gc
	 * 
	 * @param c  - The canvas to which you wish to print the drones.
	 */
	public void DisplayDrone(GraphicsContext gc) {
		gc.fillRect(x, y, 20, 20);

		/*
		 * gc.setFill(Color.RED); gc.fillText("(" + x + ", " + y + ")", x - 10, y - 5);
		 * gc.setFill(Color.BLACK);
		 */
	}

	/**
	 * Will return true if the coordinates passed in are the same as the ones stored
	 * in the class
	 * 
	 * @param sx - x position you wish to check for a drone.
	 * @param sy - y position you wish to check for a drone.
	 * @return True if this drone is at position (sx, sy). False if the drone is not
	 *         at the position (sx, sy).
	 */
	public boolean isHere(int sx, int sy) {
		if (sx == x && sy == y) {
			return true;
		}
		return false;
	}

	/**
	 * Gets a string representation of the drone
	 */
	@Override
	public String toString() {
		String ret = "Drone " + id + " is at position (" + x + ", " + y + ")";
		return ret;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}
}
