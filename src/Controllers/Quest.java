package Controllers;

import java.util.List;

import DataStorage.GameItems;
import Models.Inventory;
import Models.Item;
import Models.Player;

/**
 * List of ship parts and their locations .
 * Checks if player has all the ship parts required for beating the game
 *
 * @author jnbcb
 */
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
            System.out.println("\nYou have all the ship parts! Now we can go home!");
            System.out.println("\nYou have beaten the game and get nothing for it. Keep playing if you want...");
        } else {
            System.out.println("You are still missing parts.");
        }
    }

    private static boolean checkForParts(Inventory inventory) {
        List<Item> parts = GameItems.getShipParts();
        return parts.stream().noneMatch((part) -> (!inventory.hasItem(part.getName())));
    }
}
