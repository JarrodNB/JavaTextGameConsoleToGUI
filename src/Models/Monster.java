
package Models;

import java.io.Serializable;

import GameExceptions.CharacterException;

public class Monster extends Character implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5904180858315233690L;
	private String description;
	
	public Monster(String name,String description, int attack, int maxHealth, int gold, Inventory inventory)
			throws CharacterException {
		super(name, 0, attack, maxHealth, gold, inventory);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
