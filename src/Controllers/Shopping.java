
package Controllers;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.WeaponException;
import GameExceptions.YouDontHaveThatException;
import Models.Item;
import Models.Player;
import Models.Room;
import Models.Shop;

public class Shopping {

	private static final String HELP = "Your possible commands are buy itemName, sell itemName, examine itemName, upgrade weapon, leave, and open inventory.";
	private static Shop shop = new Shop();
	
	public static void shop(Player player, Room room) {
		System.out.println("Weclome to my shop! Take a look at my wares");
		System.out.println(shop.displayStock());
		System.out.println("Your items: " + player.getInventory().toString());
		while (true) {
			System.out.println("You have " + player.getGold() + " gold.");
			System.out.println("Would you like to buy, sell, examine an item, or leave?");
			String input = GameEngine.nextLine().toLowerCase();
			if (input.startsWith("leave")) {
				//scanner.close();
				System.out.println("You are back at home base.");
				return;
			}
			try {
				shopInput(input, player, room);
			} catch (CharacterException | ItemException | YouDontHaveThatException | WeaponException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void shopInput(String input, Player player, Room room) throws CharacterException, ItemException, YouDontHaveThatException, WeaponException {
		if (input.equals("")) {
			return;
		}
		String[] inputArray = input.split(" ");
		for (int index = 0; index < inputArray.length; index++) {
			inputArray[index] = inputArray[index].substring(0, 1).toUpperCase() + inputArray[index].substring(1, inputArray[index].length());
		}

		if (inputArray[0].equalsIgnoreCase("buy")) {
			
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					buy(player, item);
				} else {
					System.out.println("Thats not an item I have.");
				}
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					buy(player, item);
				} else {
					System.out.println("Thats not an item I have.");
				}
			} else if (inputArray.length == 2){
				String itemName = inputArray[1];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					buy(player, item);
				} else {
					System.out.println("Thats not an item I have.");
				}
			} else {
				System.out.println("Buy what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("sell")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					sell(player, item);
				} else {
					System.out.println("Thats not an item that can be sold.");
				}
				
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					sell(player, item);
				} else {
					System.out.println("Thats not an item that can be sold.");
				}
			} else if (inputArray.length == 2){
				String itemName = inputArray[1];
				Item item;
				if (shop.getStockItem(itemName) != null) {
					item = shop.getStockItem(itemName);
					sell(player, item);
				} else {
					System.out.println("Thats not an item that can be sold.");
				}
			} else {
				System.out.println("Sell what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("examine")) {
			if (inputArray.length == 3) {
				String itemName = inputArray[1] + " " + inputArray[2];
				System.out.println(shop.examineItem(itemName));
			} else if (inputArray.length == 4){
				String itemName = inputArray[1] + " " + inputArray[2] + " " + inputArray[3];
				System.out.println(shop.examineItem(itemName));
			} else if (inputArray.length == 2){
				String itemName = inputArray[1];
				System.out.println(shop.examineItem(itemName));
			} else {
				System.out.println("Examine what?");
			}
		}
		
		else if (inputArray[0].equalsIgnoreCase("open")) {
			OpenInventory.openInventory(player, room);
		}
		
		else if (inputArray[0].equalsIgnoreCase("help")) {
			System.out.println(HELP);
		}
		
		else if (inputArray[0].equalsIgnoreCase("upgrade")) {
			if (player.getInventory().hasItem("Mineral")) {
				Item item = player.getInventory().getItemNoRemoval("Mineral");
				if (item.getQuantity() >= player.getCurrentWeapon().getUpgradeCost()) {
					player.getCurrentWeapon().upgradeWeapon();
					player.getInventory().removeItem(item, player.getCurrentWeapon().getUpgradeCost());
				}
			} else {
				System.out.println("You do not have any minerals.");
			}
		}
		
		else {
			System.out.println(GameEngine.UNRECOGNIZED_COMMAND);
		}

	}
	
	private static void buy(Player player, Item item) throws CharacterException, ItemException {
		if (player.getGold() < item.getBuyValue()) {
			System.out.println("You can not afford that.");
			return;
		} else {
			player.setGold(player.getGold() - item.getBuyValue());
			player.getInventory().addItem(item);
			System.out.println("You have bought " + item.getName() + " for " + item.getBuyValue() + " gold.");
		}
	}
	
	private static void sell(Player player, Item item) throws CharacterException {
		try {
			player.getInventory().removeItem(item, 1);
			System.out.println("You sold " + item.getName() + " for " + item.getSellValue());
		} catch (YouDontHaveThatException | ItemException e) {
			System.out.println("It appears that you dont have that item!");
			return;
		}
		player.setGold(player.getGold() + item.getSellValue());
	}
	
}
