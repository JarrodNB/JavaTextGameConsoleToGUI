/*
 */
package Models;

public class ShopItem {

    private Item item;
    private int buyValue;

    public ShopItem(Item item) {
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
