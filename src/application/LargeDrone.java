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
