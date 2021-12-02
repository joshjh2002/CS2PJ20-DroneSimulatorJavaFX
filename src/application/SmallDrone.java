package application;

public class SmallDrone extends Drone {

	public SmallDrone(int x, int y) {
		super(x, y, 20);
		type = "smalldrone";
	}

	public SmallDrone(int x, int y, int xD, int yD) {
		super(x, y, 20, xD, yD);
		type = "smalldrone";
	}

	/**
	 * The event that will happen when 2 drones collide
	 *
	 * @param other - the drone object it has collided with
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone") {
			int tmp = xDir;
			setxDir(((SmallDrone) other).xDir);
			((SmallDrone) other).xDir = tmp;
			tmp = yDir;
			yDir = ((SmallDrone) other).yDir;
			((SmallDrone) other).yDir = tmp;
		} else if (other.type == "largedrone") {
			state = State.Dead;
		}
	}

}
