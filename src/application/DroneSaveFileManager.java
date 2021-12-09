package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Class to manage saving/loading a DroneArena
 *
 * @author joshh
 *
 */
public abstract class DroneSaveFileManager {
	// Filter for only .drn files
	static private ExtensionFilter extFilter = new ExtensionFilter("Drone File (*.drn)", "*.drn");

	// Actual file chooser object
	static private FileChooser fileChooser;

	/**
	 * Will load a DroneArena instance from a file
	 *
	 * @param primaryStage
	 * @return returns a DroneArena is successful, null if failed
	 */
	public static DroneArena Load(Stage primaryStage) {
		DroneArena droneArena = new DroneArena(700, 700);
		droneArena.clear();
		try {
			// Initialise FileChooser
			fileChooser = new FileChooser();
			// Add Filters (only .drn file)
			fileChooser.getExtensionFilters().add(extFilter);
			// Set title to Load
			fileChooser.setTitle("Load");
			File file = fileChooser.showOpenDialog(primaryStage);
			// If they have chosen a file
			if (file != null) {
				// if file does not exist then return null as you can't load from a file that
				// doesn't exist
				if (!file.exists())
					return null;

				// Read file
				FileInputStream fis = new FileInputStream(file);
				String path = file.getAbsolutePath();
				List<String> fileArray = Files.readAllLines(Paths.get(path));

				for (String s : fileArray) {
					String[] object = s.split(",");

					String name = object[0];
					System.out.print(name + "\n");
					int x, y, w, h, xDir, yDir;
					switch (name) {
					case "obstacle":
						x = Integer.parseInt(object[1]);
						y = Integer.parseInt(object[2]);
						w = Integer.parseInt(object[3]);
						h = Integer.parseInt(object[4]);
						droneArena.objects.add(new Obstacle(x, y, w, h));
						break;
					case "drone":
						x = Integer.parseInt(object[1]);
						y = Integer.parseInt(object[2]);
						w = Integer.parseInt(object[3]);
						xDir = Integer.parseInt(object[4]);
						yDir = Integer.parseInt(object[5]);
						if (w == 20)
							droneArena.objects.add(new SmallDrone(x, y, xDir, yDir));
						else
							droneArena.objects.add(new LargeDrone(x, y, xDir, yDir));
						break;
					case "attackdrone":
						x = Integer.parseInt(object[1]);
						y = Integer.parseInt(object[2]);
						xDir = Integer.parseInt(object[3]);
						yDir = Integer.parseInt(object[4]);
						droneArena.objects.add(new AttackDrone(x, y, xDir, yDir));
						break;
					}
				}

				fis.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return droneArena;
	}

	/**
	 * Will save a DroneArena instance to a file using serialization
	 *
	 * @param primaryStage
	 * @param droneArena
	 * @return true if successful, false if failed
	 */
	public static Boolean Save(Stage primaryStage, DroneArena droneArena) {
		try {
			// Initialise FileChooser
			fileChooser = new FileChooser();
			// Add Filters (only .drn file)
			fileChooser.getExtensionFilters().add(extFilter);
			// Set title to Save
			fileChooser.setTitle("Save");
			File file = fileChooser.showSaveDialog(primaryStage);

			// if a file has been chosen
			if (file != null) {
				// if file doesn't exist, it will create it
				if (!file.exists())
					file.createNewFile();

				// output DroneArena instance to file
				FileOutputStream fos = new FileOutputStream(file);
				for (Object obj : droneArena.objects) {
					fos.write(obj.fileOutput());
				}
				fos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
