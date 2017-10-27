
package Models;

import java.io.Serializable;

import GameExceptions.ItemException;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5443372998567698175L;
	private int id; // id of item
	private String name; // name of item
	private String description; // description of item
	private int buyValue; // shop  buy gold value of item
	private int sellValue; // shop selling gold value
	private int quantity; // number of items
	
	public Item(int id, String name, String description, int buyValue, int quantity) throws ItemException {
		setId(id);
		this.name = name;
		this.description = description;
		setBuyValue(buyValue);
		setQuantity(quantity);
		this.sellValue = this.buyValue / 2;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public int getSellValue() {
		return sellValue;
	}

	public int getQuantity() {
		return quantity;
	}

	private void setId(int id) throws ItemException {
		if (id < 0) throw new ItemException("Id can not be negative" + getClass().getSimpleName());
		this.id = id;
	}

	private void setBuyValue(int buyValue) throws ItemException {
		if (buyValue < 0) throw new ItemException("Buy value can not be negative" + getClass().getSimpleName());
		this.buyValue = buyValue;
	}

	public void setQuantity(int quantity) throws ItemException {
		if (quantity < 0) throw new ItemException("Item quantity can not be negative" + getClass().getSimpleName());
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return this.name + " Quantity: " + this.quantity;
	}
	
}
