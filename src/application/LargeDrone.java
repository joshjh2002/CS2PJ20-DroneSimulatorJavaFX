package application;

public class LargeDrone extends Drone {

	public LargeDrone(int x, int y) {
		super(x, y, 30);
		type = "largedrone";
	}

	public LargeDrone(int x, int y, int xD, int yD) {
		super(x, y, 30, xD, yD);
		type = "largedrone";
	}

	/**
	 * The event that will happen when 2 drones collide
	 *
	 * @param other - the drone object it has collided with
	 */
	@Override
	public void doCollision(Object other) {
		if (other.type == "smalldrone") {
			((SmallDrone) other).state = State.Dead;
		} else if (other.type == "largedrone") {
			int tmp = xDir;
			setxDir(((LargeDrone) other).xDir);
			((LargeDrone) other).xDir = tmp;
			tmp = yDir;
			yDir = ((LargeDrone) other).yDir;
			((LargeDrone) other).yDir = tmp;
		}
	}

}
