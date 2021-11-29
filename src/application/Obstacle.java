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
		// TODO Auto-generated constructor stub
	}

	public Obstacle(int x, int y, int w, int h) {
		super(x, y, w);
		type = "obstacle";
		this.w = w;
		this.h = h;
	}

	@Override
	public void DisplayObject(GraphicsContext gc) {
		gc.fillRect(x, y, w, h);
	}

	@Override
	public void doCollision(Object other) {
		if (other.type == "drone") {
			((Drone) other).setxDir(((Drone) other).getxDir() * -1);
			((Drone) other).setyDir(((Drone) other).getyDir() * -1);
		}
	}

	@Override
	public String toString() {
		String ret = "Obstacle " + id + " is at position (" + x + ", " + y + ")";
		return ret;
	}

	@Override
	public byte[] fileOutput() {
		String ret = "obstacle," + x + "," + y + "," + w + "," + h + "\n";
		return ret.getBytes();
	}

}
