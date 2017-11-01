package Models;

import GameExceptions.ArmorException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import GameExceptions.ItemException;
import GameExceptions.WeaponException;
import GameExceptions.YouDontHaveThatException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory extends Observable implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7184852803722533100L;
    private List<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<Item>();
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public boolean hasItem(String itemName) {
        for (Item inventoryItem : inventory) {
            if (inventoryItem.getName().equalsIgnoreCase(itemName) && inventoryItem.getQuantity() > 0) { // shouldnt need quantity
                return true;
            }
        }
        return false;
    }

    public void addItem(Item item) throws ItemException {
        for (int index = 0; index < inventory.size(); index++) {
            Item inventoryItem = inventory.get(index);
            if (item.getName().equals(inventoryItem.getName())) {
                inventoryItem.setQuantity(inventoryItem.getQuantity() + item.getQuantity());
                hasChanged();
                notifyObservers();
                return;
            }
        }
        hasChanged();
        notifyObservers();
        inventory.add(item);
    }

    public void removeItem(Item item, int quantity) throws YouDontHaveThatException, ItemException {
        for (int index = 0; index < inventory.size(); index++) {
            Item inventoryItem = inventory.get(index);
            if (inventoryItem.getName().equalsIgnoreCase(item.getName())) {
                if (inventoryItem.getQuantity() - quantity > 0) {
                    inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
                    hasChanged();
                    notifyObservers();
                    return;
                } else if (inventoryItem.getQuantity() - quantity == 0) {
                    inventory.remove(inventoryItem);
                    hasChanged();
                    notifyObservers();
                    return;
                } else {
                    throw new YouDontHaveThatException("can not remove more items then inventory has" + getClass().getSimpleName());
                }
            }
        }
        throw new YouDontHaveThatException("You do not have that item. " + getClass().getSimpleName());
    }

    public Item getItem(String name) throws YouDontHaveThatException, ItemException {
        for (int index = 0; index < inventory.size(); index++) {
            Item item = inventory.get(index);
            if (item.getName().equalsIgnoreCase(name)) {
                Item itemResult = item;
                removeItem(item, 1);
                if (itemResult instanceof Weapon){
                    try {
                        return new Weapon((Weapon) itemResult);
                    } catch (WeaponException ex) {
                        Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (itemResult instanceof Armor){
                    try {
                        return new Armor((Armor) itemResult);
                    } catch (ArmorException ex) {
                        Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    return new Item(itemResult);
                }
            }
        }
        throw new YouDontHaveThatException("You do not have that Item. " + getClass().getSimpleName());
    }

    public Item getItemNoRemoval(String itemName) throws YouDontHaveThatException {
        for (int index = 0; index < inventory.size(); index++) {
            Item item = inventory.get(index);
            if (item.getName().equalsIgnoreCase(itemName)) {
                Item itemResult = item;
                return itemResult;
            }
        }
        throw new YouDontHaveThatException("You do not have that Item. " + getClass().getSimpleName());
    }

    public void addInventory(Inventory addInventory) throws ItemException {
        for (int index = 0; index < addInventory.getInventory().size(); index++) {
            Item item = addInventory.getInventory().get(index);
            addItem(item);
        }
        hasChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        if (inventory.isEmpty()) {
            return "";
        }
        String result = "";
        for (Item item : inventory) {
            result += item.toString() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
}
