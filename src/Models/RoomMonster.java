
package Models;

import java.io.Serializable;

public class RoomMonster extends RoomObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6727914882211017866L;
	private Monster monster;
	
	public RoomMonster(Monster monster) {
		super();
		this.monster = monster;
	}

	public Monster getMonster() {
		return monster;
	}
	
}
