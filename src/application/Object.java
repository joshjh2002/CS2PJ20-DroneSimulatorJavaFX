package application;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class used to instantiate a drone
 *
 * @author joshh
 *
 */
public abstract class Object {

	static int lastID = 0;
	public int deadCounter = 0;
	public String type = "";
	protected int x, y, w, h, id;

	/**
	 * @param x coordinate at which you wish to place a drone
	 * @param y coordinate at which you wish to place a drone
	 */
	public Object(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = w;
		// lastID++;
		id = ++lastID;
	}

	/**
	 * Will draw the drone to the canvas and rotate the drone clockwise
	 *
	 * @param gc - The canvas to which you wish to print the drones.
	 */
	public void DisplayObject(GraphicsContext gc) {
		gc.fillRect(x, y, w, h);
	}

	/**
	 * The event that will happen when 2 drones collide
	 *
	 * @param other - the drone object it has collided with
	 */
	public void doCollision(Object other) {

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
	 * @return x - drone y-coordinate as int
	 */
	public int getY() {
		return y;
	}

	/**
	 * Checks if 2 drones have collided
	 *
	 * @param other - other drone
	 * @return true if they have collided
	 */
	public Boolean hasCollided(Object other) {
		if (x < other.x + other.w + 2 && x + w + 2 > other.x && y < other.y + h + 2 && h + y + 2 > other.y)
			return true;
		return false;
	}

	/**
	 * Will move the drone by the distance directions set by xDir and yDir
	 */
	public void Move() {
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
	 * Force the y-coordinate to a point
	 *
	 * @param y coordinate you wish to move the drone to
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets a string representation of the drone
	 */
	@Override
	public String toString() {
		String ret = "Drone " + id + " is at position (" + x + ", " + y + ")";
		return ret;
	}

	public abstract byte[] fileOutput();
	{
		
	}
	
}
