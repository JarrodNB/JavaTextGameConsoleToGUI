
package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import Models.Universe;
import devgame.MyScanner;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameEngine implements Serializable{

    private Universe universe;    
    
	public static void main(String[] args) throws ItemException, CharacterException {
		GameEngine engine = new GameEngine();
		engine.start();
	}
	// fix strings

	public static final String UNRECOGNIZED_COMMAND = "Command is not recognized.";
	
	public void start() throws ItemException, CharacterException {
		while (true) {
			String input = nextLine(this);
			Universe universe = null;
			if (input.equalsIgnoreCase("new game")) {
				universe = NewGame.newGame(this);
			} else if (input.equalsIgnoreCase("load game")) {
				universe = LoadGame.load(this);
			} else {
				System.out.println(UNRECOGNIZED_COMMAND);
				//scanner.close();
				continue;
			}
			try {
				if (universe != null) {
					//scanner.close();
                                        universe.setGameEngine(this);
                                        this.universe = universe; // testing
					RoomHandler.enter(universe.getCurrentRoom());
				}
			} catch (PlayerIsDeadException dead) {
				universe.getPlayer().setGold(universe.getPlayer().getGold() / 2);
				universe.setCurrentRoom(universe.getRoom("Home Base"));
				System.out.println("You have died! You lost half your gold and respawned at Home Base. Your game has been saved.");
				SaveGame.save(universe, universe.getCurrentRoom());
				start();
			} 
		}
	}
        
        public static String nextLine(GameEngine engine){
                MyScanner scanner = new MyScanner(System.in);
                if (!scanner.hasNextLine()){
                    //System.out.println("scanner does not have next line");
                synchronized(engine){
                    try {
                        //System.out.println("in wait block");
                        engine.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               // System.out.println("Assigning new scanner");
                scanner = new MyScanner(System.in);
                }
                //System.out.println("Returning command");
                return scanner.nextLine();
//                String result = null;
//                while (!scanner.hasNextLine()){
//                    //scanner.close();
//                    //scanner = null;
//                    //scanner = new MyScanner(System.in);
//                    //Thread.sleep(300);
//                    scanner.setInputStream(System.in);
//                }
//                return result = scanner.nextLine();
                
        }
        
}

