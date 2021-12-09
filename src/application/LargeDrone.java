package application;

public class LargeDrone extends Drone {

	/**
	 * Creates basic large drone at set coordinates. If this is chosen over the
	 * other constructor, the drone will have a random movement vector
	 * 
	 * @param x
	 * @param y
	 */
	public LargeDrone(int x, int y) {
		super(x, y, 30);
		type = "largedrone";
	}

	/**
	 * Creates drone at specific coordinates and movement vector
	 * 
	 * @param x
	 * @param y
	 * @param xD
	 * @param yD
	 */
	public LargeDrone(int x, int y, int xD, int yD) {
		super(x, y, 30, xD, yD);
		type = "largedrone";
	}

	/**
	 * Handles large drone collision
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone") {
			((SmallDrone) other).state = State.Dead;
		} else if (other.type == "largedrone") {
			int tmp = movementVector[0];
			setxDir(((LargeDrone) other).movementVector[0]);
			((LargeDrone) other).movementVector[0] = tmp;
			tmp = movementVector[1];
			movementVector[1] = ((LargeDrone) other).movementVector[1];
			((LargeDrone) other).movementVector[1] = tmp;
		}
	}

}
