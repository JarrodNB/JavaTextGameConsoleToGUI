
package Models;

import java.io.Serializable;

import DataStorage.GameItems;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;

public class Player extends Character implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 335996075941846318L;
	private Weapon currentWeapon;
	private Armor currentArmor;
	
	public Player(String name, int defense, int attack, int maxHealth, int gold, Inventory inventory) throws CharacterException {
		super(name, defense, attack, maxHealth, gold, inventory);
		this.currentWeapon = GameItems.getPlasmaKnife(1);
		this.currentArmor = GameItems.getPilotSuit(1);
	}
	
	public Weapon getCurrentWeapon() {
		return this.currentWeapon;
	}
	
	public int getCalcAttack() {
		if (this.currentWeapon == null) return getBaseAttack();
		return this.getBaseAttack() + this.currentWeapon.getAttack();
	}

	public int getCalcDefense() {
		if (this.currentArmor == null) return getBaseDefense();
		return this.getBaseDefense() + this.currentArmor.getDefence();
	}
	
	public void equipWeapon(String weaponName) throws ItemException, YouDontHaveThatException {
		if (getInventory().hasItem(weaponName)) {
			if (currentWeapon != null) {
				unequipWeapon();
			}
			Weapon weapon = (Weapon) this.getInventory().getItem(weaponName);
			this.currentWeapon = weapon;
		} else {
			throw new YouDontHaveThatException("You dont have that weapon");
		}
	}
	
	public void unequipWeapon() throws ItemException  {
		if (currentWeapon != null) {
			Weapon weapon = currentWeapon;
			this.currentWeapon = null;
			this.getInventory().addItem(weapon);
		}
	}
	
	public void equipArmor(String armorName) throws ItemException, YouDontHaveThatException  {
		if (getInventory().hasItem(armorName)) {
			if (currentArmor != null) {
				unequipArmor();
			}
			Armor armor = (Armor) this.getInventory().getItem(armorName);
			this.currentArmor = armor;
		} else {
			throw new YouDontHaveThatException("You dont have that armor");
		}
		
	}
	
	public void unequipArmor() throws ItemException {
		if (currentArmor != null) {
			Armor armor = currentArmor;
			this.currentArmor = null;
			this.getInventory().addItem(armor);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " Attack: " + getCalcAttack() + " Defense: " + this.getCalcDefense();
	}
	
}
