
package Controllers;

import java.io.File;

import GameExceptions.CharacterException;
import Models.Inventory;
import Models.Player;
import Models.Universe;

public class NewGame {

        @SuppressWarnings("empty-statement")
	public static Universe newGame(GameEngine engine) {
		
                    new File("C:\\Voyager\\").mkdir();
                    System.out.println("Select a name for your player.");
                    String name = GameEngine.nextLine(engine);
                    while(!validName(name)) {
                    System.out.println("Your name may only contain letters and may not be blank. Enter another name.");
                    name = GameEngine.nextLine(engine);
                    }
                    Inventory inventory = new Inventory();
                    try {
                        Player player = new Player(name, 0, 1, 30, 50, inventory);
                        //scanner.close();
                        return new Universe(player);
                    } catch (CharacterException e) {
                        //scanner.close();
                    }
                    return null;
                
           
	}
	
	private static boolean validName(String name) {
		if (name == null || name.equals("")) {
			return false;
		}
		else if (name.matches("[a-zA-Z]+")) {
			File fileCheck = new File("C:\\Voyager\\" + name + ".dat");
			if (fileCheck.exists()) {
				System.out.println("That name is taken.");
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
