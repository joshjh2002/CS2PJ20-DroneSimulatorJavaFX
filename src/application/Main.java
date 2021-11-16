package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	DroneArena droneArena;
	/**
	 * If false, the animation will not play
	 */
	Boolean playAnimation = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			// VBox is the container in which the canvas will be held
			VBox root = new VBox();

			// HBox in which the buttons will be held
			HBox buttons = new HBox();

			// Scene is the container in which the VBox and HBox will be held
			Scene scene = new Scene(root, 700, 750);

			// Adds reference to a style sheet that can be used in the future
			scene.getStylesheets().add("application/application.css");

			Canvas canvas = new Canvas();
			canvas.setWidth(scene.getWidth());
			canvas.setHeight(700);
			GraphicsContext gc = canvas.getGraphicsContext2D();

			gc.setFill(Color.BLACK);

			// Try to get image:
			try {
				Drone.img = new Image("application/drone.png");
			} catch (Exception e) {

			}

			// Add the first drone to the canvas
			droneArena = new DroneArena((int) canvas.getWidth(), (int) canvas.getHeight());
			droneArena.showDrones(gc, false);

			// Create Menu Bar
			MenuBar menuBar = new MenuBar();

			// Add 'File' tab to Menu Bar
			Menu fileMenu = new Menu("File");
			menuBar.getMenus().add(fileMenu);

			// Add 'Save' button to 'File' Tab
			MenuItem saveMenu = new MenuItem("Save");
			// When the button is pressed
			saveMenu.setOnAction(e -> {
				playAnimation = false;
				DroneSaveFileManager.Save(primaryStage, droneArena);
			});
			fileMenu.getItems().add(saveMenu);

			// Add 'Load' button to 'File' Tab
			MenuItem loadMenu = new MenuItem("Load");
			// When button is pressed
			loadMenu.setOnAction(e -> {
				playAnimation = false;
				droneArena = DroneSaveFileManager.Load(primaryStage);
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
			});
			fileMenu.getItems().add(loadMenu);

			// Create a button to add a new drone to the screen
			Button addDroneBtn = new Button("Add Drone");
			addDroneBtn.setOnMouseClicked(e -> {
				droneArena.addDrone();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
			});

			// Will either pause or play the animation depending on the current state
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

			root.getChildren().add(menuBar);
			root.getChildren().add(canvas);
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
}
