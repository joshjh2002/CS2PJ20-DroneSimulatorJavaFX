package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AttackDrone extends LargeDrone {
	public static Image img = null;

	public AttackDrone(int x, int y) {
		super(x, y);
		type = "attackdrone";
		// TODO Auto-generated constructor stub
	}

	public AttackDrone(int x, int y, int xD, int yD) {
		super(x, y, xD, yD);
		type = "attackdrone";
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public void DisplayObject(GraphicsContext gc) {
		if (img == null)
			gc.fillRect(x, y, w, h);

		if (state == State.Dead) {
			gc.drawImage(imgExpl, x, y, w, h);
			deadCounter++;
		} else {
			gc.drawImage(AttackDrone.img, x, y, w, h);
		}
	}
}
