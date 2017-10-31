package Models;

import java.io.Serializable;
import java.util.Map;

import DataStorage.GameItems;
import java.util.List;

public class Shop implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4431145987534145650L;
    private Map<String, Item> stock;
    private List<ShopItem> shopItems;

    public Shop() {
        stock = GameItems.getShopInventory();
        shopItems = GameItems.getShopItems();
    }

    public String displayStock() {
        String result = "";
        for (Map.Entry<String, Item> entry : stock.entrySet()) {
            Item item = entry.getValue();
            result += item.getName() + " " + item.getBuyValue() + "\n";
        }
        return result;
    }

    public String examineItem(String name) {
        String description;
        try {
            description = stock.get(name).getDescription();
        } catch (NullPointerException e) {
            description = null;
        }
        if (description == null) {
            return "I don't carry that item.";
        }
        return description;
    }

    public Item getStockItem(String item) {
        return stock.get(item);
    }

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

}
