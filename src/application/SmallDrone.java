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
