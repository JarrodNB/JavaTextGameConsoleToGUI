
package Models;

import Controllers.GameEngine;
import java.io.Serializable;
import java.util.Map;

import DataStorage.GameRooms;

public class Universe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 260071071241109671L;
	private Map<String, Room> rooms;
	private Player player;
	private  Room currentRoom;
        private GameEngine engine;
	
	public Universe(Player player) {
		this.player = player;
		rooms = GameRooms.getRooms();
		for (Map.Entry<String, Room> entry : rooms.entrySet()) {
			entry.getValue().setUniverse(this);
		}
		currentRoom = rooms.get("Crash Site");
	}
	
	
	public Room getRoom(String roomName) {
		return this.rooms.get(roomName);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Map<String, Room> getRooms() {
		return rooms;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
        public void setGameEngine(GameEngine engine){
            this.engine = engine;
        }
        
        public GameEngine getEngine(){
            return this.engine;
        }
}
