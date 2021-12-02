package application;

import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class used to instantiate a drone
 *
 * @author joshh
 *
 */
public abstract class Drone extends Object {

	public static Image img = null;
	public static Image imgExpl = null;
	protected State state = State.Alive;
	protected int[] movementVector;

	/**
	 * @param x coordinate at which you wish to place a drone
	 * @param y coordinate at which you wish to place a drone
	 */
	public Drone(int x, int y, int w) {
		super(x, y, w);
		type = "drone";
		Random rnd = new Random();
		movementVector = new int[2];
		movementVector[0] = 0;
		movementVector[1] = 0;
		while (movementVector[0] == 0 && movementVector[1] == 0) {
			movementVector[0] = rnd.nextInt(3) - 1;
			movementVector[1] = rnd.nextInt(3) - 1;
		}
	}

	public Drone(int x, int y, int w, int xD, int yD) {
		super(x, y, w);
		type = "drone";
		movementVector[0] = xD;
		movementVector[1] = yD;
	}

	/**
	 * Will draw the drone to the canvas and rotate the drone clockwise
	 *
	 * @param gc - The canvas to which you wish to print the drones.
	 */
	@Override
	public void DisplayObject(GraphicsContext gc) {
		if (img == null)
			gc.fillRect(x, y, w, h);

		if (state == State.Dead) {
			gc.drawImage(imgExpl, x, y, w, h);
			deadCounter++;
		} else {
			gc.drawImage(img, x, y, w, h);
		}
	}

	/**
	 * @return the drone object in string representation
	 */
	@Override
	public byte[] fileOutput() {
		String ret = "drone," + x + "," + y + "," + w + "," + movementVector[0] + "," + movementVector[1] + "\n";
		return ret.getBytes();
	}

	/**
	 * @return xDir - drone movement along the x-axis as int
	 */
	public int getxDir() {
		return movementVector[0];
	}

	/**
	 * @return yDir - drone movement along the y-axis as int
	 */
	public int getyDir() {
		return movementVector[1];
	}

	/**
	 * Will move the drone by the distance directions set by xDir and yDir
	 */
	@Override
	public void Move() {
		if (state == State.Dead)
			return;

		x += movementVector[0];
		y += movementVector[1];
	}

	/**
	 * Force the direction the drone will move on the x-axis
	 *
	 * @param x
	 */
	public void setxDir(int xDir) {
		this.movementVector[0] = xDir;
	}

	/**
	 * Force the direction the drone will move on the y-axis
	 *
	 * @param y
	 */
	public void setyDir(int yDir) {
		this.movementVector[1] = yDir;
	}

	/**
	 * Gets a string representation of the drone
	 */
	@Override
	public String toString() {
		String ret = "Drone " + id + " is at position (" + x + ", " + y + ")";
		return ret;
	}

}
