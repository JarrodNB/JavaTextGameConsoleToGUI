
package Models;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2488440222760396526L;
	private String name;
	private String description;
	private List<RoomItem> roomItems;
	private RoomMonster roomMonster;
	private Map<String, Boolean> exits; //has portal then true
	private Universe universe;
	private RoomPuzzle roomPuzzel;
	private RoomGold roomGold;
	private boolean containsShop;
	private boolean containsMechanic;
	
	public Room(String name, String description, List<RoomItem> roomItems, RoomMonster roomMonster, RoomPuzzle puzzle, RoomGold roomGold) {
		this.name = name;
		this.description = description;
		this.roomItems = roomItems;
		this.roomMonster = roomMonster;
		this.roomPuzzel = puzzle;
		this.roomGold = roomGold;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<RoomItem> getRoomItems() {
		return roomItems;
	}

	public RoomMonster getRoomMonster() {
		return roomMonster;
	}

	public Map<String, Boolean> getExits() {
		return exits;
	}

	public Universe getUniverse() {
		return this.universe;
	}
	
	public RoomPuzzle getRoomPuzzle() {
		return this.roomPuzzel;
	}
	
	public RoomGold getRoomGold() {
		return roomGold;
	}
	
	public void setExits(Map<String, Boolean> exits) {
		this.exits = exits;
	}
	
	public void setUniverse(Universe universe) {
		this.universe = universe;
	}
	
	public boolean isExitInRoom(String exit) {
		for (Map.Entry<String, Boolean> entry : exits.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(exit)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsShop() {
		return containsShop;
	}
	
	public void setContainsShop(boolean shop) {
		this.containsShop = shop;
	}
	
	public boolean containsMechanic() {
		return containsMechanic;
	}
	public boolean setContainsMechanic() {
		return containsMechanic;
	}

	public void setContainsMechanic(boolean containsMechanic) {
		this.containsMechanic = containsMechanic;
	}

	@Override
	public String toString() {
		return this.name + " " + this.description;
	}
}
