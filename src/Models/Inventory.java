
package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;

public class Inventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7184852803722533100L;
	private List<Item> inventory;
	
	public Inventory() {
		inventory = new ArrayList<Item>();
	}
	
	private List<Item> getInventory(){
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
	
	public void addItem(Item item) throws ItemException  {
		for (int index = 0; index < inventory.size(); index++) {
			Item inventoryItem = inventory.get(index);
			if (item.getName().equals(inventoryItem.getName())) {
				inventoryItem.setQuantity(inventoryItem.getQuantity() + item.getQuantity());
				//System.out.println(item + " added to what you had + " + inventoryItem.getQuantity()); // testing
				return;
			}
		}
		//System.out.println(item + " Added"); // testing
		inventory.add(item);
	}
	
	public void removeItem(Item item, int quantity) throws YouDontHaveThatException, ItemException {
		for (int index = 0; index < inventory.size(); index++) {
			Item inventoryItem = inventory.get(index);
			if (inventoryItem.getName().equalsIgnoreCase(item.getName())) {
				if (inventoryItem.getQuantity() - quantity > 0) {
					inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
					//System.out.println(inventoryItem.getName() + " - " + quantity + " removed");// testing
					return;
				} else if (inventoryItem.getQuantity() - quantity == 0) {
					inventory.remove(inventoryItem);
					//System.out.println(inventoryItem.getName() + " removed"); // testing
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
				return itemResult;
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
	
	public void addInventory(Inventory addInventory) throws ItemException  {
		for (int index = 0; index < addInventory.getInventory().size(); index ++) {
			Item item = addInventory.getInventory().get(index);
			addItem(item);
		}
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
		return result.substring(0, result.length()-2);
	}
}
