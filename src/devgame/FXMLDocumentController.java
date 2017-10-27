
package devgame;

import Controllers.GameEngine;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import Models.Item;
import Models.Player;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField commandField;

    private PipedOutputStream outputStream;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;

    GameEngine engine;

    Player player;

    Thread thread;

    @FXML
    private Label playerName;
    @FXML
    private Label playerHP;
    @FXML
    private Label playerGold;
    @FXML
    private ListView<Item> inventoryListView;
    @FXML
    private Label playerAttack;
    @FXML
    private Label playerDefense;
    @FXML
    private Label playerEquipment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        textArea.setEditable(false);
        textArea.setWrapText(true);
    }

    @FXML
    private void submitCommand(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendText();
            commandField.clear();
        }
        Platform.runLater(()-> playerStats());

    }

    public void appendText(String str) {
        Platform.runLater(() -> textArea.appendText(str));
    }

    private void startGame(String choice) {
        engine = new GameEngine();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    StringBufferInputStream s = new StringBufferInputStream(choice);
                    System.setIn(s);
                    engine.start();
                } catch (ItemException | CharacterException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    private void sendText() {
        StringBufferInputStream s = new StringBufferInputStream(commandField.getText());
        System.setIn(s);
        synchronized (GameEngine.LOCK) {
            GameEngine.LOCK.notifyAll();
        }
    }

    @FXML
    private void newGame(ActionEvent event) {
        startGame("new game");
    }

    @FXML
    private void loadGame(ActionEvent event) {
        startGame("load game");
    }

    private void playerStats() {
        if (engine != null) {
            if (engine.getUniverse() != null) {
                player = engine.getUniverse().getPlayer();
                if (player != null) {
                    playerName.setText(player.getName());
                    playerHP.setText(String.valueOf(player.getHealthPoints()));
                    playerGold.setText(String.valueOf(player.getGold()));
                    playerAttack.setText(String.valueOf(player.getCalcAttack()));
                    playerDefense.setText(String.valueOf(player.getCalcDefense()));
                    playerEquipment.setText(player.getEquipment());
                    listView();
                }
            }
        }
    }
    
    private void listView(){
        ObservableList<Item> invList = FXCollections.<Item>observableArrayList(player.getInventory().getInventory());
        // item obserable
        // loop through inventory adding items to obslist
        inventoryListView.setItems(invList);
    }
}
