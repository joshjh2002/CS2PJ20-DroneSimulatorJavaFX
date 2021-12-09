/**
 *
 */
package application;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author joshh
 *
 */
public class Obstacle extends Object {

	/**
	 * @param x
	 * @param y
	 * @param w
	 */
	public Obstacle(int x, int y, int w) {
		super(x, y, w);
		type = "obstacle";
		Random rnd = new Random();
		w = 20 + rnd.nextInt(10) * rnd.nextInt(4);
		h = 20 + rnd.nextInt(10) * rnd.nextInt(4);
	}

	/**
	 * Creates a new drone with specific coordinates and dimentions
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Obstacle(int x, int y, int w, int h) {
		super(x, y, w);
		type = "obstacle";
		this.w = w;
		this.h = h;
	}

	/**
	 * Overrides how an obstacle is drawn. It will be drawn as a black box
	 */
	@Override
	public void DisplayObject(GraphicsContext gc) {
		gc.fillRect(position[0], position[1], w, h);
	}

	/**
	 * Handles what happens if a drone collides with an obstacle
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone" || other.type == "largedrone" || other.type == "attackdrone") {
			((Drone) other).setxDir(((Drone) other).getxDir() * -1);
			((Drone) other).setyDir(((Drone) other).getyDir() * -1);
		}
	}

	/**
	 * Used to generate save information which will be written to a text file
	 */
	@Override
	public byte[] fileOutput() {
		String ret = "obstacle," + getX() + "," + getY() + "," + w + "," + h + "\n";
		return ret.getBytes();
	}

	/**
	 * Generates string representation of the obstacle for the information panel
	 */
	@Override
	public String toString() {
		String ret = "Obstacle " + id + " is at position (" + getX() + ", " + getY() + ")";
		return ret;
	}

}
