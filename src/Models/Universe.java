package Models;

import java.io.Serializable;
import java.util.Map;

import DataStorage.GameRooms;
import java.util.Observable;

public class Universe extends Observable implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 260071071241109671L;
    private Map<String, Room> rooms;
    private Player player;
    private volatile Room currentRoom;
    private volatile String gameState;

    public Universe(Player player) {
        this.player = player;
        rooms = GameRooms.getRooms();
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            entry.getValue().setUniverse(this);
        }
        currentRoom = rooms.get("Crash Site");
        this.gameState = "room";
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

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        setChanged();
        notifyObservers(gameState);
        this.gameState = gameState;
    }

}
