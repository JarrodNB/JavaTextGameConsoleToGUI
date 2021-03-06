package Controllers;

import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;
import Models.Player;

/**
 * Controller for healing item use
 *
 * @author jnbcb
 */
public class UseItem {

    /**
     * Heals player and removes item
     *
     * @param player
     * @param itemName
     */
    public static void useItem(Player player, String itemName) {
        int playerMax = player.getMaxHealth();
        try {
            if (itemName.equalsIgnoreCase("medicine")) {
                player.getInventory().getItem(itemName);
                player.heal(playerMax / 2);
            } else if (itemName.equalsIgnoreCase("elixir")) {
                player.getInventory().getItem(itemName);
                player.heal(playerMax);
            } else {
                System.out.println("That is not a useable item.");
            }
        } catch (YouDontHaveThatException | ItemException e) {
            System.out.println("It appears that you don't have that item.");
            System.out.println(e.getMessage());
        }
    }

}
