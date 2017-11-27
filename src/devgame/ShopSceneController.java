/*
 */
package devgame;

import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.WeaponException;
import GameExceptions.YouDontHaveThatException;
import Models.Inventory;
import Models.Item;
import Models.Player;
import Models.Shop;
import Models.ShopItem;
import Models.Weapon;
import Sound.SoundPlayer;
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
    private Observer observer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init(Player player, Inventory inventory, Observer observer) {
        this.observer = observer;
        this.player = player;
        player.addObserver(this);
        this.inventory = inventory;
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
            PopUp.launch("You do not have enough gold.");
        } else {
            (new SoundPlayer()).pPlay(SoundPlayer.BUY_SELL);
            player.setGold(player.getGold() - item.getBuyValue());
            inventory.addItem(item.getItem());
        }
    }

    @FXML
    private void sellItem(MouseEvent event) throws YouDontHaveThatException, ItemException, CharacterException {
        Item item = sellListView.getSelectionModel().getSelectedItem();
        if (item == null) {
            return;
        } else if ( item.getId() > 2){
            PopUp.launch("You can not sell ship parts.");
            return;
        }
        (new SoundPlayer()).pPlay(SoundPlayer.BUY_SELL);
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

    @FXML
    private void upgradeWeapon(ActionEvent event) throws YouDontHaveThatException, ItemException, WeaponException {
        Weapon weapon = player.getCurrentWeapon();
        if (weapon == null){
            PopUp.launch("You do not have a weapon equipped.");
            return;
        }
        if (inventory.hasItem("Mineral")) {
            Item mineral = inventory.getItemNoRemoval("Mineral");
            if (mineral.getQuantity() >= weapon.getUpgradeCost()) {
                inventory.removeItem(mineral, weapon.getUpgradeCost());
                weapon.upgradeWeapon();
                (new SoundPlayer()).pPlay(SoundPlayer.UPGRADE);
                (this).update(null, null);
                observer.update(null, null);
            } else {
                PopUp.launch("You do not have enough minerals. You need " + weapon.getUpgradeCost() + ".");
            }
        } else {
            PopUp.launch("You do not have any minerals.");
        }
    }

}
