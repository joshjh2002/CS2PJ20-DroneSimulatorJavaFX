package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
			VBox vBox = new VBox();

			// HBox in which the buttons will be held
			HBox canvasAndLabel = new HBox();

			HBox buttons = new HBox();

			// Scene is the container in which the VBox and HBox will be held
			Scene scene = new Scene(vBox, 950, 750);

			// Adds reference to a style sheet that can be used in the future
			scene.getStylesheets().add("application/application.css");

			Canvas canvas = new Canvas();
			canvas.setWidth(700);
			canvas.setHeight(700);
			GraphicsContext gc = canvas.getGraphicsContext2D();

			// Scrollable, read-only area to display debug information
			TextArea informationPanel = new TextArea();
			informationPanel.setEditable(false);

			gc.setFill(Color.BLACK);

			// Try to get drone image:
			try {
				Drone.img = new Image("application/drone.png");
			} catch (Exception e) {

			}

			// Try to get explosion image:
			try {
				Drone.imgExpl = new Image("application/explosion.png");
			} catch (Exception e) {

			}

			// Try to get attack drone image:
			try {
				AttackDrone.img = new Image("application/attack.png");
			} catch (Exception e) {

			}

			// Add the first drone to the canvas
			// droneArena = DroneSaveFileManager.Load(primaryStage, "application/save.drn");
			if (droneArena == null)
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

			// Add 'Help' Tab to menu bar
			MenuItem helpMenu = new MenuItem("Help");
			fileMenu.getItems().add(helpMenu);

			helpMenu.setOnAction(e -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Help");
				alert.setHeaderText("How use the Drone Simulator");
				alert.setContentText(
						"Press 'Add Drone' to add a new drone to the screen. This will choose a drone of 20x20, or 30x30, size and add it to the screen at a random position\n\nPress the Play/Pause button to start or stor the simulation.");
				alert.show();
			});

			// Create a button to add a new drone to the screen
			Button addDroneBtn = new Button("Add Drone");
			addDroneBtn.setOnMouseClicked(e -> {
				droneArena.addDrone();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
				informationPanel.setText(droneArena.toString());
			});

			// Create a button to add a new attack drone to the screen
			Button addAttackBtn = new Button("Add Attack Drone");
			addAttackBtn.setOnMouseClicked(e -> {
				droneArena.addAttackDrone();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
				informationPanel.setText(droneArena.toString());
			});

			// Create a button to add a new obstacle to the screen
			Button addObstacleBtn = new Button("Add Obstacle");
			addObstacleBtn.setOnMouseClicked(e -> {
				droneArena.addObstacle();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
				informationPanel.setText(droneArena.toString());
			});

			// Create a button to clear the Arena
			Button clearBtn = new Button("Clear Arena");
			clearBtn.setOnMouseClicked(e -> {
				droneArena.clear();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				droneArena.showDrones(gc, false);
				informationPanel.setText(droneArena.toString());
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

			// Add drone on Mouse Click
			canvas.setOnMouseClicked(e -> {
				droneArena.addDrone((int) e.getX(), (int) e.getY());
				droneArena.showDrones(gc, false);
				informationPanel.setText(droneArena.toString());
			});

			new AnimationTimer() // create timer
			{
				@Override
				public void handle(long currentNanoTime) {
					if (playAnimation) {
						gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
						droneArena.showDrones(gc, true);
						informationPanel.setText(droneArena.toString());
					}
				}
			}.start(); // start the timer

			vBox.getChildren().add(menuBar);
			vBox.getChildren().add(canvasAndLabel);

			canvasAndLabel.getChildren().add(canvas);
			canvasAndLabel.getChildren().add(informationPanel);
			informationPanel.setText(droneArena.toString());

			vBox.getChildren().add(buttons);

			buttons.getChildren().add(addDroneBtn);
			buttons.getChildren().add(addObstacleBtn);
			buttons.getChildren().add(addAttackBtn);
			buttons.getChildren().add(clearBtn);
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
