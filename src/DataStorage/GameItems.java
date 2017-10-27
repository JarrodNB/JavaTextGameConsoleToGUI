
package DataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GameExceptions.ArmorException;
import GameExceptions.ItemException;
import GameExceptions.WeaponException;
import Models.Armor;
import Models.Item;
import Models.Weapon;

public class GameItems {

	public static Armor getPilotSuit(int quantity) {
		try {
			return new Armor(0, "Pilot Suit", "Standard leather clothing all pilots use. It’s not very protective", 100, quantity, 1);
		} catch (ItemException | ArmorException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Armor getLawEnforcementGear(int quantity) {
		try {
			return new Armor(1, "Law Enforcement Gear", "Protective gear worn by colony law enforcement, it offers decent protection", 400, quantity, 3);
		} catch (ItemException | ArmorException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Armor getMilitaryGear(int quantity) {
		try {
			return new Armor(2, "Military Gear", "Protective gear worn by colony law military officers, it offers good protection", 700, quantity, 5);
		} catch (ItemException | ArmorException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Weapon getPlasmaKnife(int quantity) {
		try {
			return new Weapon(0, "Plasma Knife", "Very common self-defense weap-on popular among colonists.", 50, quantity, 1, 1);
		} catch (ItemException | WeaponException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Weapon getPlasmaSword(int quantity) {
		try {
			return new Weapon(1, "Plasma Sword", "Old weapon mainly found in museums around the colony, no longer common place but more powerful than the knife.", 250, quantity, 3, 2);
		} catch (ItemException | WeaponException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Weapon getPlasmaPistol(int quantity) {
		try {
			return new Weapon(2, "Plasma Pistol", "Inexpensive gun used by colony law enforcement to protect from space pirates. Easy to find in shops.", 400, quantity, 5, 3);
		} catch (ItemException | WeaponException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Weapon getLaserRifle(int quantity) {
		try {
			return new Weapon(3, "Laser Rifle", "Military grade weaponry used by colony militia’s. Expensive.", 800, quantity, 7, 5);
		} catch (ItemException | WeaponException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Weapon getRocketLauncher(int quantity) {
		try {
			return new Weapon(4, "Rocket Launcher", "Very powerful weapon only used by highly trained soldiers. The limited availability of this weapon makes it expensive in shops.", 2000, quantity, 9, 8);
		} catch (ItemException | WeaponException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Item getMedicine(int quantity) {
		try {
			return new Item(0, "Medicine", "Sophisticated medicine capable of healing half of the body’s wounds.", 50, quantity);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Item getElixir(int quantity) {
		try {
			return new Item(1, "Elixir", "Very advanced medicine capable of healing all of the body’s wounds.", 100, quantity);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Item getMineral(int quantity) {
		try {
			return new Item(2, "Mineral", "Resource found mainly on mining colonies. Used to make materials.", 700, quantity);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Item getRadar() {
		try {
			return new Item(3, "Radar", "A piece of the ship that allows us to detect objects in space.", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Item getCockpit() {
		try {
			return new Item(4, "Cockpit", "A piece of the ship that allows us to pilot the ship.", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Item getEngine() {
		try {
			return new Item(5, "Engine", "We need this to power the ship!", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Item getComms() {
		try {
			return new Item(6, "Communication Network", "We need this to communicate.", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Item getWings() {
		try {
			return new Item(7, "Wings", "We need this to fly the ship.", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Item getDefenseTurret() {
		try {
			return new Item(7, "Defense Turret", "We need this to protect the ship.", 1, 1);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return null;	
		}
	}
	
	public static Map<String, Item> getShopInventory() {
		Map<String, Item> stock = new HashMap<String, Item>();
		stock.put("Medicine", getMedicine(1));
		stock.put("Elixir", getElixir(1));
		stock.put("Mineral", getMineral(1));
		stock.put("Plasma Knife", getPlasmaKnife(1));
		stock.put("Plasma Sword", getPlasmaSword(1));
		stock.put("Plasma Pistol", getPlasmaPistol(1));
		stock.put("Laser Rifle", getLaserRifle(1));
		stock.put("Rocket Launcher", getRocketLauncher(1));
		stock.put("Pilot Suit", getPilotSuit(1));
		stock.put("Law Enforcement Gear", getLawEnforcementGear(1));
		stock.put("Military Gear", getMilitaryGear(1));
		return stock;
	}
	
	public static List<Item> getShipParts(){
		List<Item> list = new ArrayList<>();
		list.add(getCockpit());
		list.add(getComms());
		list.add(getDefenseTurret());
		list.add(getEngine());
		list.add(getRadar());
		list.add(getWings());
		return list;
	}
	
}
