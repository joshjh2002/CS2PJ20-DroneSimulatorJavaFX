package application;

public class SmallDrone extends Drone {

	/**
	 * Creates new drone at specific coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public SmallDrone(int x, int y) {
		super(x, y, 20);
		type = "smalldrone";
	}

	/**
	 * Creates new drone at specific coordinates with specific movement vectors
	 * 
	 * @param x
	 * @param y
	 * @param xD
	 * @param yD
	 */
	public SmallDrone(int x, int y, int xD, int yD) {
		super(x, y, 20, xD, yD);
		type = "smalldrone";
	}

	/**
	 * Handles collision for small drones
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone") {
			int tmp = movementVector[0];
			setxDir(((SmallDrone) other).movementVector[0]);
			((SmallDrone) other).movementVector[0] = tmp;
			tmp = movementVector[1];
			movementVector[1] = ((SmallDrone) other).movementVector[1];
			((SmallDrone) other).movementVector[1] = tmp;
		} else if (other.type == "largedrone") {
			state = State.Dead;
		}
	}

}
