
package DataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Room;
import Models.RoomGold;
import Models.RoomItem;
import Models.RoomMonster;
import Models.RoomPuzzle;

public class GameRooms {
	//done
	public static Room getCrashSite() {
		String description = "You wake up in a forest, dazed but uninjured. You stand up and look at your ship, which is missing many of it’s vital pieces"
				+ " from your encounter with a band of space pirates.\n There is no way that the ship "
				+ "can fly in it’s current condition, so you must find another way to get off this pla"
				+ "net.\n To the north, there appears to be a building, so maybe it would be best to investigate.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getWornoutRobot(GameItems.getElixir(1), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Storage Container");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Laboratory", false);
		exits.put("Portal Room", false);
		Room room = new Room("Crash Site", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getLaboratory() {
		String description = "The remains of the destroyed robot lay on the floor. The fight "
				+ "with the robot destroyed all machinery in the room, meaning that "
				+ "getting information from the room is no longer an option.\n"
				+ " Behind you is the exit of the lab, and forward, there is a door to another room.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getSixthPuzzle());
		RoomGold roomGold = new RoomGold(100, "Storage Container");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Crash Site", false);
		exits.put("Portal Room", false);
		Room room = new Room("Laboratory", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getPortalRoom() {
		String description = "You enter a room with a single portal,"
				+ " which is currently turned on and active.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Barrel"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getWornoutRobot(GameItems.getPlasmaKnife(1), 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Crash Site", false);
		exits.put("Laboratory", false);
		exits.put("Home Base", true);
		Room room = new Room("Portal Room", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done. add quest giver
	public static Room getHomeBase() {
		String description = "Upon exiting the portal, you enter a base located on the moon. The room is filled with"
				+ " 4 portals labelled Mercury, Venus, Earth, and Mars.\n"
				+ " There is also a shop keeper there, who looks at you, hoping that you came to buy something. Try typing shop to interact with him.\n"
				+ "Your ship mechanic has also set up here. Perhaps you should talk to him by typing mechanic.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Portal Room", true);
		exits.put("Mars Surface", true);
		exits.put("Umbriel", true);
		exits.put("Triton", true);
		exits.put("Venus Surface", true);
		exits.put("Mercury Surface", true);
		exits.put("Titan", true);
		exits.put("Europa", true);
		Room room = new Room("Laboratory", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setContainsShop(true);
		room.setContainsMechanic(true);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getUmbriel() {
		String description = "Of all the moons of Uranus, Umbriel is the darkest, "
				+ "very little sunlight reaches the surface.\n Unwilling to found a colony on this"
				+ " moon, the humans created a large prison complex here";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getElixir(1), "Bag"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getDefenseDrone(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Barrel");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Home Base", true);
		exits.put("Titania", true);
		exits.put("Ariel", true);
		exits.put("Oberon", true);
		Room room = new Room("Umbriel", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getTitania() {
		String description = "Known for its high winds, numerous craters and canyons,"
				+ " humans established underground colonies here to hunt for resources";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMineral(1), "Hole"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getDefenseDrone(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Umbriel", true);
		exits.put("Ariel", true);
		Room room = new Room("Titania", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getAriel() {
		String description = "Ariel has vast wide-open plains that humans once used for farming.\n"
				+ " After this moon's abandonment."
				+ " The Agricultural villages remain scattered throughout the colony may prove useful.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Bag"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getRuffians(GameItems.getElixir(1), 0));
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getSecondPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Titania", true);
		exits.put("Umbriel", true);
		exits.put("Oberon", true);
		Room room = new Room("Ariel", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getOberon() {
		String description = "This large, heavily cratered moon has been set "
				+ "up as a research station by humans.\n"
				+ " Now abandoned, the research station gives rise to many secrets...";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getElixir(1), "Cabinet"));
		roomItems.add(new RoomItem(GameItems.getMineral(1), "Hole"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getMadScientist(GameItems.getRadar(), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Ariel", true);
		exits.put("Umbriel", true);
		Room room = new Room("Oberon", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getTriton() {
		String description = "Triton is an extremely large moon. Its "
				+ "surface is completely covered by a mostly frozen nitrogen water-ice crust.\n"
				+ " Since the moon is cold and barren, humans avoid colonizing it.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getFireGiant(GameItems.getCockpit(), 100));
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getTenthPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Home Base", true);
		exits.put("Nereid", true);
		Room room = new Room("Triton", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getNereid() {
		String description = "This is the third largest moon of Neptune"
				+ " and contained valuable resources sought after by humans.\n"
				+ " They eventually mined the moon hollow and left only equipment behind.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMineral(1), "Hole"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Triton", true);
		exits.put("Neso", true);
		exits.put("Naiad", true);
		Room room = new Room("Nereid", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getNeso() {
		String description = "Neso is a small non-spherical moon that is 48"
				+ " million kilo-meters from Neptune. This moon was used as a "
				+ "religious cult who believed Neptunes big blue spot contained secrets to immortality.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Bag"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getRuffians(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Nereid", true);
		Room room = new Room("Neso", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getNaiad() {
		String description = "This moon is the closest satellite to Neptune and was"
				+ " once a thriving trading colony that humans lived on.\n Due to tidal"
				+ " stretching humans had to leave before the moon would be ripped apart.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMineral(2), "Bag"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getThirdPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Nereid", true);
		exits.put("Thalassa", true);
		Room room = new Room("Naiad", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getThalassa() {
		String description = "Thalassa is a small, bare moon. Its' only purpose"
				+ " for human colonists was to use it for sightseeing.\n"
				+ " Some people may have left some valuable things behind.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirate(null, 200));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(300, "Storage Container");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Naiad", true);
		Room room = new Room("Thalassa", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	
	public static Room getMarsSurface() {
		String description = "You are on the surface of mars. There does not seem to be much here.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Deimos", true);
		exits.put("Phobos", true);
		exits.put("Trap Room", false);
		exits.put("Wasteland", false);
		exits.put("Home Base", true);
		Room room = new Room("Mars Surface", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getDeimos() {
		String description = "Human civilization was crucial to colonize on one of these moons"
				+ " as human population was growing"
				+ ",\n so this moon was filled with plenty of hidden medication under the sand dunes.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Barrel"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getDefenseDrone(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mars Surface", true);
		Room room = new Room("Deimos", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getPhobos() {
		String description = "The floor is riddled with holes here. You make a note to yourself to be careful, or you might fall.\n"
				+ " You look around, and spot a couple of useful items across the room.\n"
				+ " You can’t see them clearly because of the monster blocking your way. Better kill it first.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Barrel"));
		roomItems.add(new RoomItem(GameItems.getLaserRifle(1), "Hole"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getFireGiant(null, 200));
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getFourthPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mars Surface", true);
		Room room = new Room("Phobos", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getTrapRoom() {
		String description = "When you enter the room ,you see a trap holes"
				+ " in front of you as well as across the room\n and the room is filled with "
				+ "bunch of furniture and hidden treasure  box for the rewards.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Bag"));
		roomItems.add(new RoomItem(GameItems.getPlasmaSword(1), "Barrel"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getDefenseDrone(null, 0));
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getFifthPuzzle());
		RoomGold roomGold = new RoomGold(100, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mars Surface", false);
		Room room = new Room("Trap Room", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getWasteLand() {
		String description = "You walk into most disastrous room on the planet and the monster"
				+ " is waiting for you in the corner.\n There is a medication "
				+ "in the room and few other rewards once you kill the monster.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Bag"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mars Surface", false);
		Room room = new Room("Wasteland", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	
	public static Room getVenusSurface() {
		String description = "Venus leaves much to be desired. Take a look around.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Lake", false);
		exits.put("Field", false);
		exits.put("Ghost Town", false);
		exits.put("Volcano", false);
		exits.put("Home Base", true);
		Room room = new Room("Venus Surface", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getLake() {
		String description = "The city is covered with a lake on the left side with a "
				+ "yacht on the corner to get to the white house for surprises.\n"
				+ " On the left side, we have the rock covered tomb. Be adventurous to find hidden stuff!!";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getLaserRifle(1), "Hole"));
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Bag"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getEighthPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Venus Surface", false);
		Room room = new Room("Lake", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getGhostTown() {
		String description = "This is the best part of the city!! Due to enough amount "
				+ "of sun UV rays, it got sand dunes and a city where there is life,\n"
				+ " filled with pools and church. WATCH OUT!! There is something you could get harmed by.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Storage Container"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getRuffians(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Venus Surface", false);
		Room room = new Room("Ghost Town", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getVolcano() {
		String description = "This the smallest city of the Venus and "
				+ "also the city of natural disasters (Volcanoes and hurricanes)\n"
				+ " on your way, there is wooden door blocking the way.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getElixir(1), "Hole"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getSeventhPuzzle());
		RoomGold roomGold = new RoomGold(100, "Bag");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Venus Surface", false);
		Room room = new Room("Volcano", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getField() {
		String description = "ALERT!! There is a gigantic terrific creature on a "
				+ "hunger strike which is carnivorous."
				+ " \nThis is the last stage of the Venus. Good Luck!!";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Hole"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getRuffians(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Venus Surface", false);
		Room room = new Room("Field", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	
	public static Room getMercurySurface() {
		String description = "You see a large building in the distance.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Outside Power-Plant", false);
		exits.put("Generator Room", false);
		exits.put("Destroyed Wing", false);
		exits.put("Manager's Room", false);
		exits.put("Home Base", true);
		Room room = new Room("Mercury Surface", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getOutsidePowerPlant() {
		String description = "You stand outside of what appears to be a large white building"
				+ " that is gated off.\n While it is not clear what exactly it is used for, "
				+ "at first glance it appears to be a power plant of some sort.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Cabinet"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirate(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mercury Surface", false);
		Room room = new Room("Outside Power-Plant", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getGeneratorRoom() {
		String description = "The center of the power plant appears to be a power plant.\n "
				+ "While it is still able to function, there is very clearly visible damage"
				+ " to the inside of the room, and a few parts appear to be missing.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(2), "Storage Container"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getFireGiant(GameItems.getEngine(), 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mercury Surface", false);
		Room room = new Room("Generator Room", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getDestroyedWing() {
		String description = "The west side of the plant is destroyed beyond repair.\n"
				+ " The back wall is non-existent, the only evidence"
				+ " that it even existed at any point is a pile of rubble surrounding the hole.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getElixir(1), "Storage Container"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mercury Surface", false);
		Room room = new Room("Destroyed Wing", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getManagerRoom() {
		String description = "You enter what appears to be an office."
				+ " A man sits behind a desk and stares at you, wondering what you are doing "
				+ "in here. While he does not seem dangerous, he is wary of your presence.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getDefenseDrone(GameItems.getPlasmaSword(1), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Mercury Surface", false);
		Room room = new Room("Manager's Room", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getTitan() {
		String description = "This large moon is a barren orange color due to its "
				+ "atmosphere.\nThe Surface of Titan is flat as it lacks huge craters and towering mountains. Tall dunes stretch across the surface"
				+ " far and wide.\n Abandoned settlements stretch across the surface.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getElixir(1), "Bag"));
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Barrel"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirate(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Hole");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Home Base", true);
		exits.put("Enceladus", true);
		exits.put("Mimas", true);
		exits.put("Pandora", true);
		exits.put("Atlas", true);
		Room room = new Room("Titan", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getEnceladus() {
		String description = "Enceladus is a white-like color due to the surface being made up "
				+ "entirely of ice.\n Massive geysers frequently spew water from the moon's "
				+ "Subsurface Ocean into space.\n Abandoned mining operations surround "
				+ "the geysers to collect materials around the area.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMineral(1), "Hole"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirate(null, 100));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(100, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Titan", true);
		exits.put("Pandora", true);
		exits.put("Atlas", true);
		Room room = new Room("Enceladus", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getMimas() {
		String description = "Mimas is a small moon that was considered to insubstantial"
				+ " to establish a human colony,\n as a result, "
				+ "pirates who raid trade shipments established a base here, now long abandoned.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirateCaptian(GameItems.getComms(), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Titan", true);
		exits.put("Pandora", true);
		exits.put("Atlas", true);
		Room room = new Room("Mimas", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getPandora() {
		String description = "Pandora is an extremely small heavily cratered, moon. "
				+ "\nIn the future, it is used as a staging facility to facilitate"
				+ " travel between the human colonies. The remains of a star-port is still there.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMedicine(1), "Storage Container"));
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getSpacePirate(GameItems.getPlasmaPistol(1), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(500, "Hole");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Titan", true);
		exits.put("Enceladus", true);
		exits.put("Mimas", true);
		Room room = new Room("Pandora", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getAtlas() {
		String description = "Atlas is an extremely small disk-shaped moon that orbits "
				+ "closely around Saturn's rings.\n Future nations agreed to make this moon "
				+ "neutral to all governments and the moon became a hotbed for tourism to view Saturn’s incredible rings."
				+ " \nAbandoned hotels and attractions sprawl across the surface of Atlas.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(200, "Cabinet");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Titan", true);
		exits.put("Enceladus", true);
		exits.put("Mimas", true);
		Room room = new Room("Atlas", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getEuropa() {
		String description = "This is a big moon of Jupiter and has subsurface oceans.\n"
				+ " Human colonist set up civilizations"
				+ " in these subsurface oceans that also contain life native to the moon.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getKraken(GameItems.getWings(), 0));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Home Base", true);
		exits.put("Ganymede", true);
		exits.put("Io", true);
		Room room = new Room("Europa", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getGanymede() {
		String description = "Ganymede is the biggest moon in the solar system which"
				+ " attracted many humans to come and colonize it.\n "
				+ "It rapidly became the most densely populated place in the solar system.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = new RoomGold(200, "Bag");
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Europa", true);
		exits.put("Io", true);
		exits.put("Amalthea", true);
		Room room = new Room("Ganymede", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getIo() {
		String description = "This moon has over 400 active volcanoes. "
				+ "\nScientist attempted to set up geothermal power plants on the moon but the volcanoes "
				+ "were less dormant than the scientist expected leaving only remains behind.";
		List<RoomItem> roomItems = null;
		RoomMonster roomMonster = new RoomMonster(GameMonsters.getFireGiant(null, 200));
		RoomPuzzle roomPuzzle = null;
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Europa", true);
		exits.put("Ganymede", true);
		exits.put("Amalthea", true);
		Room room = new Room("Io", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	// done
	public static Room getAmalthea() {
		String description = "This moon has an amazing view of Jupiter.\n Because of its great"
				+ " view a religious cult was set up on the moon to worship"
				+ " Jupiter's big red spot.";
		List<RoomItem> roomItems = new ArrayList<>();
		roomItems.add(new RoomItem(GameItems.getMineral(1), "Barrel"));
		RoomMonster roomMonster = null;
		RoomPuzzle roomPuzzle = new RoomPuzzle(GamePuzzles.getFirstPuzzle());
		RoomGold roomGold = null;
		Map<String, Boolean> exits = new HashMap<String, Boolean>();
		exits.put("Ganymede", true);
		exits.put("Io", true);
		Room room = new Room("Amalthea", description, roomItems, roomMonster, roomPuzzle, roomGold);
		room.setExits(exits);
		return room;
	}
	
	public static Map<String, Room> getRooms(){
		Map<String, Room> rooms = new HashMap<String, Room>();
		rooms.put("Crash Site", getCrashSite());
		rooms.put("Laboratory", getLaboratory());
		rooms.put("Portal Room", getPortalRoom());
		rooms.put("Home Base", getHomeBase());
		rooms.put("Umbriel", getUmbriel());
		rooms.put("Titania", getTitania());
		rooms.put("Ariel", getAriel());
		rooms.put("Oberon", getOberon());
		rooms.put("Triton", getTriton());
		rooms.put("Nereid", getNereid());
		rooms.put("Neso", getNeso());
		rooms.put("Naiad", getNaiad());
		rooms.put("Thalassa", getThalassa());
		rooms.put("Mars Surface", getMarsSurface());
		rooms.put("Phobos", getPhobos());
		rooms.put("Deimos", getDeimos());
		rooms.put("Trap Room", getTrapRoom());
		rooms.put("Wasteland", getWasteLand());
		rooms.put("Venus Surface", getVenusSurface());
		rooms.put("Lake", getLake());
		rooms.put("Ghost Town", getGhostTown());
		rooms.put("Volcano", getVolcano());
		rooms.put("Field", getField());
		rooms.put("Mercury Surface", getMercurySurface());
		rooms.put("Outside Power-Plant", getOutsidePowerPlant());
		rooms.put("Generator Room", getGeneratorRoom());
		rooms.put("Destroyed Wing", getDestroyedWing());
		rooms.put("Manager's Room", getManagerRoom());
		rooms.put("Titan", getTitan());
		rooms.put("Enceladus", getEnceladus());
		rooms.put("Mimas", getMimas());
		rooms.put("Pandora", getPandora());
		rooms.put("Atlas", getAtlas());
		rooms.put("Europa", getEuropa());
		rooms.put("Ganymede", getGanymede());
		rooms.put("Io", getIo());
		rooms.put("Amalthea", getAmalthea());
		return rooms;
	}
}
