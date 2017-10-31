package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import Models.Player;
import Models.Universe;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameEngine implements Serializable {

    private Universe universe = null;
    public static final Object LOCK = new Object();

//    public static void main(String[] args) throws ItemException, CharacterException {
//        GameEngine engine = new GameEngine();
//        engine.start();
//    }

    public static final String UNRECOGNIZED_COMMAND = "Command is not recognized.";

    public void start(Universe universe) {
        try {
            RoomHandler.enter(universe.getCurrentRoom());
        } catch (PlayerIsDeadException dead) {
            try {
                universe.getPlayer().setGold(universe.getPlayer().getGold() / 2);
                universe.setCurrentRoom(universe.getRoom("Home Base"));
                System.out.println("You have died! You lost half your gold and respawned at Home Base. Your game has been saved.");
                System.out.println("Try new game or load game.");
                SaveGame.save(universe, universe.getCurrentRoom());
            } catch (CharacterException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ItemException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CharacterException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String nextLine() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            scanner = new Scanner(System.in);
        }
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return nextLine();
        }

    }

    public Universe getUniverse() {
        return this.universe;
    }

    public void guiStart(Universe universe){
        this.universe = universe;
        start(universe);
    }
}
