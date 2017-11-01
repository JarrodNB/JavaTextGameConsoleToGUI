package Models;

import java.io.Serializable;

public class RoomObject implements Serializable{

    public boolean isInRoom;

    public RoomObject() {
        //isInRoom = true;
    }

    public boolean isInRoom() {
        return isInRoom;
    }

    public void setIsInRoom(boolean isInRoom) {
        this.isInRoom = isInRoom;
    }
}
