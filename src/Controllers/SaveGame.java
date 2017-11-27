package Controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Models.Room;
import Models.Universe;

/**
 * Saves the game in a .dat file
 *
 * @author jnbcb
 */
public class SaveGame {

    public static void save(Universe universe, Room room) {
        universe.setCurrentRoom(room);
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("C:\\Voyager\\" + universe.getPlayer().getName() + ".dat");
        } catch (FileNotFoundException e) {
        }
        ObjectOutputStream objectOut = null;
        try {
            objectOut = new ObjectOutputStream(fileOut);
        } catch (IOException e) {
        }
        try {
            objectOut.writeObject(universe);
        } catch (IOException e) {
        }
    }
}
