package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import Models.Player;
import Models.Puzzle;
import Models.RoomPuzzle;
import Sound.SoundPlayer;

/**
 * Controller for puzzles
 *
 * @author jnbcb
 */
public class SolvePuzzle {

    private static final String HELP = "Your possible commands are to give answer to the puzzle, give up, or get hint.";

    /**
     * Displays the puzzle and parses the players input. Rewards items if player
     * completes puzzle.
     *
     * @param roomPuzzle
     * @param player
     * @throws CharacterException
     * @throws ItemException
     */
    public static void solvePuzzle(RoomPuzzle roomPuzzle, Player player) throws CharacterException, ItemException {
        Puzzle puzzle = roomPuzzle.getPuzzle();
        System.out.println(puzzle.getQuestion());
        while (true) {
            String userInput = GameEngine.nextLine();
            if (userInput.equalsIgnoreCase("get hint")) {
                System.out.println(puzzle.getHint());
            } else if (userInput.equalsIgnoreCase(puzzle.getAnswer())) {
                System.out.println("Thats correct!");
                (new SoundPlayer()).pPlay(SoundPlayer.PUZZLE_SOLVED);
                break;
            } else if (userInput.equalsIgnoreCase("give up")) {
                System.out.println("You have given up and will be returned to your room in shame.");
                SoundPlayer.play(SoundPlayer.RETREAT);
                return;
            } else if (userInput.equalsIgnoreCase("help")) {
                System.out.println(HELP);
            } else {
                System.out.println("Thats not correct");
            }
        }

        if (puzzle.getGoldReward() != 0) {
            player.setGold(player.getGold() + puzzle.getGoldReward());
            System.out.println("You have been awarded " + puzzle.getGoldReward() + " gold!");
        }

        if (puzzle.getItemReward() != null) {
            player.getInventory().addItem(puzzle.getItemReward());
            System.out.println("You have received a " + puzzle.getItemReward().getName() + "!");
        }
        System.out.println("You have completed the puzzle! You are now back in the room.");
        roomPuzzle.setIsInRoom(false);
    }
}
