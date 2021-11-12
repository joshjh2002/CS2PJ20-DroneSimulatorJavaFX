package application;

import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {

	Boolean playAnimation = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root, 700, 700);
			scene.getStylesheets().add("application/application.css");

			Canvas canvas = new Canvas();
			canvas.setWidth(scene.getWidth());
			canvas.setHeight(scene.getHeight() - 25);
			GraphicsContext gc = canvas.getGraphicsContext2D();

			gc.setFill(Color.BLACK);

			DroneArena droneArena = new DroneArena((int) canvas.getWidth(), (int) canvas.getHeight());
			droneArena.showDrones(gc, false);

			Button addDroneBtn = new Button("Add Drone");
			addDroneBtn.setOnMouseClicked(e -> {
				droneArena.addDrone();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
			});

			Button pausePlayBtn = new Button("Play");
			pausePlayBtn.setOnMouseClicked(e -> {
				if (!playAnimation) {
					pausePlayBtn.setText("Pause");
					playAnimation = true;
				} else {
					playAnimation = false;
					pausePlayBtn.setText("Play");
				}
			});

			new AnimationTimer() // create timer
			{
				@Override
				public void handle(long currentNanoTime) {
					if (playAnimation) {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						droneArena.showDrones(gc, true);
					}
				}
			}.start(); // start it

			root.getChildren().add(canvas);

			HBox buttons = new HBox();

			root.getChildren().add(buttons);
			buttons.getChildren().add(addDroneBtn);
			buttons.getChildren().add(pausePlayBtn);

			primaryStage.setResizable(false);
			primaryStage.setTitle("Drone Simulator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
