package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import Models.Universe;
import Sound.SoundPlayer;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Starts the game and handles logic for player death
 *
 * @author jnbcb
 */
public class GameEngine implements Serializable {

    private Universe universe = null;
    public static final Object LOCK = new Object();
    public static final String UNRECOGNIZED_COMMAND = "Command is not recognized.";

    /**
     * Starts the game and handles player death
     *
     * @param universe
     */
    public void guiStart(Universe universe) {
        try {
            this.universe = universe;
            RoomHandler.enter(universe.getCurrentRoom());
        } catch (PlayerIsDeadException dead) {
            try {
                (new SoundPlayer()).pPlay(SoundPlayer.DEAD);
                universe.getPlayer().setGold(universe.getPlayer().getGold() / 2);
                universe.setCurrentRoom(universe.getRoom("Home Base"));
                System.out.println("You have died! You lost half your gold and respawned at Home Base. Your game has been saved.");
                System.out.println("Try new game or load game.");
                SaveGame.save(universe, universe.getCurrentRoom());
            } catch (CharacterException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ItemException | CharacterException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Replaces scanner.nextLine() throughout the game.
     * Waits for input before waiting to read
     *
     * @return
     */
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

}
