
package Controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Models.Room;
import Models.Universe;

public class SaveGame {

	public static void save(Universe universe, Room room) {
		universe.setCurrentRoom(room);
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("C:\\Voyager\\" + universe.getPlayer().getName() + ".dat");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			objectOut.writeObject(universe);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
