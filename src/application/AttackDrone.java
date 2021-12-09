package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class is responsible for handling the Attack Drone behaviour
 * 
 * @author joshh
 *
 */
public class AttackDrone extends LargeDrone {
	protected static Image img = null;

	/**
	 * Adds a new attack drone
	 * 
	 * @param x
	 * @param y
	 */
	public AttackDrone(int x, int y) {
		super(x, y);
		type = "attackdrone";
	}

	/**
	 * Adds a new attack drone with a specific movement vector
	 * 
	 * @param x
	 * @param y
	 * @param xD
	 * @param yD
	 */
	public AttackDrone(int x, int y, int xD, int yD) {
		super(x, y, xD, yD);
		type = "attackdrone";
	}

	/**
	 * Determines how to draw the attack drone
	 */
	@Override
	public void DisplayObject(GraphicsContext gc) {
		if (img == null)
			gc.fillRect(getX(), getY(), w, h);

		if (state == State.Dead) {
			gc.drawImage(imgExpl, getX(), getX(), w, h);
			deadCounter++;
		} else {
			gc.drawImage(AttackDrone.img, getX(), getY(), w, h);
		}
	}

	/**
	 * Determines attack drone collision behaviour
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone") {
			((SmallDrone) other).state = State.Dead;
		} else if (other.type == "largedrone") {
			((LargeDrone) other).state = State.Dead;
		} else if (other.type == "attackdrone") {
			int tmp = movementVector[0];
			setxDir(((AttackDrone) other).movementVector[0]);
			((AttackDrone) other).movementVector[0] = tmp;
			tmp = movementVector[1];
			movementVector[1] = ((AttackDrone) other).movementVector[1];
			((AttackDrone) other).movementVector[1] = tmp;
		}
	}

	/**
	 * Overrides fileOutput for drone. This is used to save the drone
	 */
	@Override
	public byte[] fileOutput() {
		String ret = "attackdrone," + getX() + "," + getY() + "," + getxDir() + "," + getyDir() + "\n";
		return ret.getBytes();
	}

	/**
	 * Overrides toString to print the data accuratly to the information panel
	 */
	@Override
	public String toString() {
		String ret = "Attack Drone" + id + " is at position (" + getX() + ", " + getY() + ")";
		return ret;
	}
}
