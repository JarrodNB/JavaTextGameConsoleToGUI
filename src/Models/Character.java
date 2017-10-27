
package Models;

import java.io.Serializable;

import GameExceptions.CharacterException;
import GameExceptions.MonsterIsDeadException;
import GameExceptions.PlayerIsDeadException;

public class Character implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8505969773094231323L;
	private String name;
	private int maxHealth;
	private int healthPoints;
	private int baseDefense;
	private int gold;
	private int baseAttack;
	private Inventory inventory;

	public Character(String name, int defense, int attack, int maxHealth, int gold, Inventory inventory) throws CharacterException {
		this.name = name;
		setBaseDefense(defense);
		setGold(gold);
		setBaseAttack(attack);
		this.healthPoints = maxHealth;
		setMaxHealth(maxHealth);
		setInventory(inventory);
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	public int getBaseDefense() {
		return baseDefense;
	}

	public int getGold() {
		return gold;
	}
	
	public int getBaseAttack() {
		return baseAttack;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	protected void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	private void setMaxHealth(int hp) throws CharacterException {
		if (hp < 5) throw new CharacterException("max hp is too low" + getClass().getSimpleName());
		maxHealth = hp;
	}
	
	public void heal(int amount) {
		if (this.healthPoints + amount > maxHealth) {
			this.healthPoints = maxHealth;
		} else {
			this.healthPoints += amount;
		}
	}
	
	public void takeDamage(int amount) throws PlayerIsDeadException, MonsterIsDeadException {
		//if (amount < 0)
		if (this.healthPoints - amount <= 0) {
			if (this instanceof Player) {
				throw new PlayerIsDeadException();
			} else {
				throw new MonsterIsDeadException();
			}
		} else {
			this.healthPoints -= amount;
		}
	}
	
	private void setBaseDefense(int defense) throws CharacterException {
		if (defense < 0) throw new CharacterException("base defense cannot be less then 0" + getClass().getSimpleName());
		this.baseDefense = defense;
	}

	public void setGold(int gold) throws CharacterException {
		if (gold < 0) throw new CharacterException("gold can not be less then 0" + getClass().getSimpleName());
		this.gold = gold;
	}
	
	private void setBaseAttack(int attack) throws CharacterException {
		if (attack < 0) throw new CharacterException("base attack can not be less then 0" + getClass().getSimpleName());
		this.baseAttack = attack;
	}
	
	@Override
	public String toString() {
		return this.name + " Health: " + this.healthPoints + " Gold: " + this.gold;
	}
	
}
