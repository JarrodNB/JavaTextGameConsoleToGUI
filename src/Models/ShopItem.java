/*
 */
package Models;

import GameExceptions.ArmorException;
import GameExceptions.ItemException;
import GameExceptions.WeaponException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopItem {

    private Item item;
    private int buyValue;

    public ShopItem(Item item) {
        this.item = item;
        this.buyValue = item.getBuyValue();
    }

    public Item getItem() throws ItemException {
        if (item instanceof Weapon) {
            try {
                return new Weapon((Weapon) item);
            } catch (WeaponException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (item instanceof Armor) {
            try {
                return new Armor((Armor) item);
            } catch (ArmorException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return new Item(item);
        }
        return null;
    }

    public int getBuyValue() {
        return buyValue;
    }

    @Override
    public String toString() {
        return item.getName() + " " + buyValue;
    }

}
