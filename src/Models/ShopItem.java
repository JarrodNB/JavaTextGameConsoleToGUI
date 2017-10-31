/*
 */

package Models;

/**Class: 
 * @author Jarrod Bailey
 * Version 1.0
 * Course: ITEC 2140 Spring 2017 Section 6
 * Written: , 2017
 * 
 *
 */

public class ShopItem {

    private Item item;
    private int buyValue;
    
    public ShopItem(Item item){
        this.item = item;
        this.buyValue = item.getBuyValue();
    }

    public Item getItem() {
        return item;
    }

    public int getBuyValue() {
        return buyValue;
    }

    @Override
    public String toString() {
        return item.getName() + " " + buyValue;
    }
    
    
}
