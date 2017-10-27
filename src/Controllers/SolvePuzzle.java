
package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import Models.Player;
import Models.Puzzle;
import Models.Room;
import Models.RoomPuzzle;

public class SolvePuzzle {
	
	private static final String HELP = "Your possible commands are to give answer to the puzzle, give up, or get hint.";
	
	public static void solvePuzzle(RoomPuzzle roomPuzzle, Player player, Room room) throws CharacterException, ItemException {
		Puzzle puzzle = roomPuzzle.getPuzzle();
		System.out.println(puzzle.getQuestion());
		while (true) {
			String userInput = GameEngine.nextLine(room.getUniverse().getEngine());
			if (userInput.equalsIgnoreCase("get hint")) {
				System.out.println(puzzle.getHint());
			} else if (userInput.equalsIgnoreCase(puzzle.getAnswer())) {
				System.out.println("Thats correct!");
				break;
			} else if (userInput.equalsIgnoreCase("give up")) {
				System.out.println("You have given up and will be returned to your room in shame.");
				//scanner.close();
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
		//scanner.close();
		System.out.println("You have completed the puzzle! You are now back in the room.");
		roomPuzzle.setIsInRoom(false);
	}
}
