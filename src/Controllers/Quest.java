
package Controllers;

import java.util.List;

import DataStorage.GameItems;
import Models.Inventory;
import Models.Item;
import Models.Player;

public class Quest {

	public static void display(Player player) {
		System.out.println("In order to repair the ship we need to find some replacement parts.");
		System.out.println("The engine can be found somewhere around Mercury.");
		System.out.println("The cockpit can be found somewhere around Neptune.");
		System.out.println("The radar can be found somewhere around Uranus");
		System.out.println("The wings can be found somewhere around Jupiter");
		System.out.println("The communication network can be found somewhere around Saturn");
		System.out.println("The defense turret can be found somewhere around Venus");
		if (checkForParts(player.getInventory())) {
			System.out.println("You have all the ship parts! Now we can go home!");
			System.out.println("You have beaten the game and get nothing for it. Keep playing if you want");
		} else {
			System.out.println("You are still missing parts.");
		}
	}

	private static boolean checkForParts(Inventory inventory) {
		List<Item> parts = GameItems.getShipParts();
		for (Item part : parts) {
			if (!inventory.hasItem(part.getName())){
				return false;
			}
		}
		return true;
	}
}
