
package Controllers;

import GameExceptions.ItemException;
import GameExceptions.PlayerIsDeadException;
import GameExceptions.YouDontHaveThatException;
import Models.Armor;
import Models.Item;
import Models.Player;
import Models.Room;
import Models.Weapon;

public class OpenInventory {

	private static final String HELP = "Your possible commands are use itemName, examine itemName, equip itemName, unequip weapon/armor, stats, or exit.";

	public static void openInventory(Player player, Room room) {
		System.out.println(player.getInventory().toString());
		System.out.println(HELP);
		while (true) {
			String input = GameEngine.nextLine();
			if (input.equalsIgnoreCase("exit")) {
				//scanner.close();
				return;
			}
			try {
				inventoryInput(player, input);
			} catch (YouDontHaveThatException | ItemException | PlayerIsDeadException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void inventoryInput(Player player, String input) throws YouDontHaveThatException, PlayerIsDeadException, ItemException {
		if (input.equals("")) {
			return;
		}
		
		String[] inputArray = input.split(" ");

		if (inputArray[0].equalsIgnoreCase("use")) {
                    switch (inputArray.length) {
                        case 3:
                            {
                                String itemName = inputArray[1] + " " + inputArray[2];
                                UseItem.useItem(player, itemName);
                                break;
                            }
                        case 4:
                            {
                                String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
                                UseItem.useItem(player, itemName);
                                break;
                            }
                        case 2:
                            {
                                String itemName = inputArray[1];
                                UseItem.useItem(player, itemName);
                                break;
                            }
                        default:
                            System.out.println("Use what?");
                            break;
                    }
		}
		
		else if (inputArray[0].equalsIgnoreCase("stats")) {
			System.out.println(player);
		}
		
		else if (inputArray[0].equalsIgnoreCase("equip")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				equip(player, itemName);
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				equip(player, itemName);
			} else if (inputArray.length == 2){
				String itemName = inputArray[1];
				equip(player, itemName);
			} else {
				System.out.println("Equip what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("unequip")) {
			if (inputArray.length > 1 && inputArray[1].equals("weapon")) {
				player.unequipWeapon();
				System.out.println("Your weapon has been unequipped.");
			}
			else if (inputArray.length > 1 && inputArray[1].equals("armor")) {
				player.unequipArmor();
				System.out.println("Your armor has been unequipped");
			} else {
				System.out.println("Unequip what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("examine")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			} else if (inputArray.length == 2) {
					String itemName = inputArray[1];
					System.out.println(player.getInventory().getItemNoRemoval(itemName).getDescription());
			} else {
				System.out.println("Examine what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("help")) {
			System.out.println(HELP);
		}
		
		else {
			System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
		}
	}
	
	private static void equip(Player player, String itemName) throws YouDontHaveThatException, ItemException {
		if (player.getInventory().hasItem(itemName)) {
			Item item = player.getInventory().getItemNoRemoval(itemName);
			if (item instanceof Weapon) {
				player.equipWeapon(itemName);
			} else if (item instanceof Armor) {
				player.equipArmor(itemName);
			} else {
				System.out.println("That can not be equipped!");
			} 
		}
		else {
			System.out.println("Thats not an item you have.");
		}
	}
	
}
