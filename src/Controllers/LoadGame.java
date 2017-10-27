
package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


import Models.Universe;

public class LoadGame {

	public static Universe load(GameEngine engine) {
		System.out.println("What is the name of your player?");
		String fileName = "C:\\Voyager\\" + GameEngine.nextLine() + ".dat";
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e2) {
			System.out.println("That save game does not exist");
			return null;
		}
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(fileStream);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		try {
			Universe universe = (Universe) inputStream.readObject();
			return universe;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
