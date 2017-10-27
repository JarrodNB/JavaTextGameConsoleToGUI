
package Models;

import java.io.Serializable;

import GameExceptions.ItemException;
import GameExceptions.WeaponException;

public class Weapon extends Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4970997342022388785L;
	private int attack;
	private int upgradeAmount;
	private int upgradeLevel;
	private int upgradeCost;
	private static int COST_INCREASE = 1;
	private static int LEVEL_INCREASE = 1;
	
	public Weapon(int id, String name, String description, int buyValue, int quantity, int attack,
			int upgradeAmount) throws WeaponException, ItemException {
		super(id, name, description, buyValue, quantity);
		setAttack(attack);
		setUpgradeAmount(upgradeAmount);
		setUpgradeLevel(0);
		setUpgradeCost(1);
	}

	public int getAttack() {
		return attack;
	}

	public int getUpgradeAmount() {
		return upgradeAmount;
	}

	public int getUpgradeLevel() {
		return upgradeLevel;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	private void setAttack(int attack) throws WeaponException {
		if (attack < 1) throw new WeaponException("weapon attack must be atleast 1" + getClass().getSimpleName());
		this.attack = attack;
	}

	private void setUpgradeAmount(int upgradeAmount) throws WeaponException {
		if (upgradeAmount < 1) throw new WeaponException("weapon upgrade amount must be atleast 1" + getClass().getSimpleName());
		this.upgradeAmount = upgradeAmount;
	}

	private void setUpgradeLevel(int upgradeLevel) throws WeaponException {
		if (upgradeLevel < 0) throw new WeaponException("weapon upgrade level must not be negative" + getClass().getSimpleName());
		this.upgradeLevel = upgradeLevel;
	}

	private void setUpgradeCost(int upgradeCost) throws WeaponException {
		if (upgradeCost < 1) throw new WeaponException("upgrade cost must be atleast 1" + getClass().getSimpleName());
		this.upgradeCost = upgradeCost;
	}
	
	public void upgradeWeapon() throws WeaponException{
		setAttack(getAttack() + getUpgradeAmount());
		setUpgradeCost(getUpgradeCost() + Weapon.COST_INCREASE);
		setUpgradeLevel(getUpgradeAmount() + Weapon.LEVEL_INCREASE);
	}
	
	@Override
	public String toString() {
		String result = super.toString();
		result += " Attack: " + this.attack + " Upgrade: " + this.upgradeAmount;
		return result;
	}
}
