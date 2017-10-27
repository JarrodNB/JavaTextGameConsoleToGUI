
package Models;

import java.io.Serializable;

public class RoomGold extends RoomObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176874659536892431L;
	private int gold;
	private String containerName;
	
	public RoomGold(int gold, String containerName) {
		super();
		this.gold = gold;
		this.containerName = containerName;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public String getContainerName() {
		return containerName;
	}
}
