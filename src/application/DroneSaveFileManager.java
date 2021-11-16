package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	static ExtensionFilter extFilter = new ExtensionFilter("Drone File (*.drn)", "*.drn");
	static FileChooser fileChooser;

	/**
	 * Will load a DroneArena instance from a file
	 *
	 * @param primaryStage
	 * @return returns a DroneArena is successful, null if failed
	 */
	public static DroneArena Load(Stage primaryStage) {
		DroneArena droneArena = null;
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
				ObjectInputStream ois = new ObjectInputStream(fis);
				droneArena = (DroneArena) ois.readObject();
				ois.close();
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
				ObjectOutputStream ous = new ObjectOutputStream(fos);
				ous.writeObject(droneArena);
				ous.close();
				fos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
