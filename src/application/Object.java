package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class used to instantiate a drone
 *
 * @author joshh
 *
 */
public abstract class Object {

	static int lastID = 0;
	protected int deadCounter = 0;
	protected int position[];
	protected String type = "";
	protected int w, h, id;
	/**
	 * @param x coordinate at which you wish to place a drone
	 * @param y coordinate at which you wish to place a drone
	 */
	public Object(int x, int y, int w) {
		position = new int[2];
		position[0] = x;
		position[1] = y;
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
		gc.fillRect(getX(), getY(), w, h);
	}

	/**
	 * The event that will happen when 2 drones collide
	 *
	 * @param other - the drone object it has collided with
	 */
	public void doCollision(Object other) {
		return;
	}

	public abstract byte[] fileOutput();

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
		return position[0];
	}

	/**
	 * @return x - drone y-coordinate as int
	 */
	public int getY() {
		return position[1];
	}

	/**
	 * Checks if 2 drones have collided
	 *
	 * @param other - other drone
	 * @return true if they have collided
	 */
	public Boolean hasCollided(Object other) {
		if (this.getX() < other.getX() + other.w && this.getX() + this.w > other.getX()
				&& this.getY() < other.getY() + other.h && this.h + this.getY() > other.getY())
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
		this.position[0] = x;
	}

	/**
	 * Fo.position[0]e the y-coordinate to a point
	 *
	 * @param y coordinate you wish to move the drone to
	 */
	public void setY(int y) {
		this.position[1] = y;
	}

	/**
	 * Gets a string representation of the drone
	 */
	@Override
	public String toString() {
		String ret = "Drone " + id + " is at position (" + getX() + ", " + getY() + ")";
		return ret;
	}

}
