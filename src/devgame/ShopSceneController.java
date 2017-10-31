/*
 */
package devgame;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;
import Models.Inventory;
import Models.Item;
import Models.Player;
import Models.Shop;
import Models.ShopItem;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jnbcb
 */
public class ShopSceneController implements Initializable, Observer {

    @FXML
    private ListView<ShopItem> buyListView;
    @FXML
    private ListView<Item> sellListView;
    @FXML
    private Label playerGold;

    private Player player;

    private Inventory inventory;

    private Shop shop;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init(Player player) {
        this.player = player;
        player.addObserver(this);
        inventory = player.getInventory();
        inventory.addObserver(this);
        shop = new Shop();
        buyListView.getItems().addAll(shop.getShopItems());
        Platform.runLater(() -> updateThread());
    }

    @FXML
    private void buyItem(MouseEvent event) throws CharacterException, ItemException {
        ShopItem item = buyListView.getSelectionModel().getSelectedItem();
        if (item == null) {
            return;
        }
        if (item.getBuyValue() > player.getGold()) {

        } else {
            player.setGold(player.getGold() - item.getBuyValue());
            inventory.addItem(item.getItem());
        }
    }

    @FXML
    private void sellItem(MouseEvent event) throws YouDontHaveThatException, ItemException, CharacterException {
        Item item = sellListView.getSelectionModel().getSelectedItem();
        if (item == null || item.getId() > 2) {
            return;
        }
        inventory.removeItem(item, 1);
        player.setGold(player.getGold() + item.getSellValue());
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> updateThread());
    }

    private void updateThread() {
        playerGold.setText("" + player.getGold());
        sellListView.getItems().clear();
        sellListView.getItems().addAll(inventory.getInventory());
    }

    @FXML
    private void close(ActionEvent event) {
        ((Stage) playerGold.getScene().getWindow()).close();
    }

}
