package application;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class used to instantiate a drone
 *
 * @author joshh
 *
 */
public class Drone implements Serializable {

	public static Image img = null;
	static int lastID = 0;
	private static final long serialVersionUID = 9159655539969594695L;
	private int x, y, w, h, id, xDir = 0, yDir = 0;

	/**
	 * @param x coordinate at which you wish to place a drone
	 * @param y coordinate at which you wish to place a drone
	 */
	public Drone(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = w;
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
		if (img == null)
			gc.fillRect(x, y, w, h);
		else
			gc.drawImage(img, x, y, w, h);

		/*
		 * gc.setFill(Color.RED); gc.fillText("(" + x + ", " + y + ")", x - 10, y - 5);
		 * gc.setFill(Color.BLACK);
		 */
	}

	/**
	 * The event that will happen when 2 drones collide
	 *
	 * @param other - the drone object it has collided with
	 */
	public void doCollision(Drone other) {
		int tmp = xDir;
		setxDir(other.xDir);
		other.xDir = tmp;
		tmp = yDir;
		yDir = other.yDir;
		other.yDir = tmp;
	}

	/**
	 * @return id - drone id as int
	 */
	public int getId() {
		return id;
	}

	public int getW() {
		return w;
	}

	/**
	 * @return x - drone x-coordinate as int
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return xDir - drone movement along the x-axis as int
	 */
	public int getxDir() {
		return xDir;
	}

	/**
	 * @return y - drone y-coordinate as int
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return yDir - drone movement along the y-axis as int
	 */
	public int getyDir() {
		return yDir;
	}

	/**
	 * Checks if 2 drones have collided
	 * 
	 * @param d - other drone
	 * @return true if they have collided
	 */
	public Boolean hasCollided(Drone d) {
		if (x < d.x + d.w + 2 && x + w + 2 > d.x && y < d.y + h + 2 && h + y + 2 > d.y)
			return true;
		return false;
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
	 * Will move the drone by the distance directions set by xDir and yDir
	 */
	public void Move() {
		x += xDir;
		y += yDir;
	}

	/**
	 * Force the x-coordinate to a point
	 *
	 * @param x coordinate you wish to move the drone to
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Force the direction the drone will move on the x-axis
	 *
	 * @param x
	 */
	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	/**
	 * Force the y-coordinate to a point
	 *
	 * @param y coordinate you wish to move the drone to
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Force the direction the drone will move on the y-axis
	 *
	 * @param y
	 */
	public void setyDir(int yDir) {
		this.yDir = yDir;
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
