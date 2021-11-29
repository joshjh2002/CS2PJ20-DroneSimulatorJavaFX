package application;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class used to instantiate a drone
 *
 * @author joshh
 *
 */
public class Drone extends Object {

	public static Image img = null;
	public static Image imgExpl = null;
	public State state = State.Alive;
	private int xDir = 0, yDir = 0;

	/**
	 * @param x coordinate at which you wish to place a drone
	 * @param y coordinate at which you wish to place a drone
	 */
	public Drone(int x, int y, int w) {
		super(x, y, w);
		type = "drone";
		Random rnd = new Random();

		while (xDir == 0 && yDir == 0) {
			xDir = rnd.nextInt(3) - 1;
			yDir = rnd.nextInt(3) - 1;
		}
	}

	public Drone(int x, int y, int w, int xD, int yD) {
		super(x, y, w);
		type = "drone";
		xDir = xD;
		yDir = yD;
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
	@Override
	public void doCollision(Object other) {
		if (other.type == "drone") {
			if (other.w > w && other.getClass() == this.getClass()) {
				state = State.Dead;
			} else if (w > other.w && other.getClass() == this.getClass()) {

				((Drone) other).state = State.Dead;
			} else {
				int tmp = xDir;
				setxDir(((Drone) other).xDir);
				((Drone) other).xDir = tmp;
				tmp = yDir;
				yDir = ((Drone) other).yDir;
				((Drone) other).yDir = tmp;
			}

		}
	}

	/**
	 * @return xDir - drone movement along the x-axis as int
	 */
	public int getxDir() {
		return xDir;
	}

	/**
	 * @return yDir - drone movement along the y-axis as int
	 */
	public int getyDir() {
		return yDir;
	}

	/**
	 * Will move the drone by the distance directions set by xDir and yDir
	 */
	@Override
	public void Move() {
		if (state == State.Dead)
			return;

		x += xDir;
		y += yDir;
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

	@Override
	public byte[] fileOutput() {
		String ret = "drone," + x + "," + y + "," + w + "," + xDir + "," + yDir + "\n";
		return ret.getBytes();
	}
}
