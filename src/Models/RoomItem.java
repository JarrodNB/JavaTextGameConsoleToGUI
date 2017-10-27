
package Models;

import java.io.Serializable;

public class RoomItem extends RoomObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 851642506960818744L;
	private Item item;
	private String containerName;
	
	public RoomItem(Item item, String containerName) {
		super();
		this.item = item;
		this.containerName = containerName;
	}

	public Item getItem() {
		return item;
	}
	
	public String getContainerName() {
		return containerName;
	}

}
