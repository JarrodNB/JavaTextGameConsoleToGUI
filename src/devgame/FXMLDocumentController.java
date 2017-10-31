package devgame;

import Controllers.GameEngine;
import Controllers.UseItem;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
import GameExceptions.YouDontHaveThatException;
import Models.Armor;
import Models.Inventory;
import Models.Item;
import Models.Player;
import Models.Universe;
import Models.Weapon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable, Observer {

    // DONE create player in controller and set into game engine. add observer when creating, then change a value to notify immedialty
    // Done listview with all commands.... get list of possible commands from room for room. 
    // Done universe .. gamestate in universe string fight, room, shop , openinventory ..... list view observes gamestate ... listview changes commands based on gamestate
    // something wrong with upgrade and mineral? no?
    // DONE player observable to updating after upgrading
    // mechanic mention missing shop parts
    // DONE lower rocket launcher upgrade
    // DONE KRAKEN strengthen hard monsters ...
    // DONE outside power plant is not showing stuff?
    // add map button to show scene with map picture
    // Done should be able to change gamestate from just roomhandler
    // Done shop gamestate to launch scene
    // fight scene .. pass monster and player
    // Done change universe current room as i go room from room
    // Done hp to red when low
    // Fixed hitting load game then new game causes problems.... started 2 threads... // thread.stop();
    // make variables volatile
    // Done add inventory as observable and in update method if instance of inventory ... updated listview
    // Done add haslooked to room .. use as part of get room commands
    // No puzzles multiple choice
    // Done hp blink
    // item right click to get description?
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

    private GameEngine engine;

    private Player player;

    private Thread thread;

    private Timeline timeLine;

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
    @FXML
    private ListView<String> commandListView;

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
        timeLine = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> playerHP.setVisible(false)),
                new KeyFrame(Duration.seconds(0.1), evt -> playerHP.setVisible(true)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        commandField.setVisible(false);
    }

    @FXML
    private void submitCommand(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendText(commandField.getText());
            commandField.clear();
        }
    }

    public void appendText(String str) {
        Platform.runLater(() -> textArea.appendText(str));
    }

    private void sendText(String command) {
        StringBufferInputStream s = new StringBufferInputStream(command);
        System.setIn(s);
        synchronized (GameEngine.LOCK) {
            GameEngine.LOCK.notifyAll();
        }
    }

    @FXML
    private void newGame(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Player name");
        dialog.setContentText("Please enter a valid player name.");
        Optional<String> name = dialog.showAndWait();
        if (name.isPresent()) {
            String pName = name.get();
            if (validateName(pName)) {
                player = createPlayer(pName);
                startNewGame(player);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Name");
                alert.setHeaderText("Invalid player name");
                alert.setContentText("Please use only letters.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void loadGame(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Load Game");
        dialog.setContentText("Please the name of your hero.");
        Optional<String> name = dialog.showAndWait();
        if (name.isPresent()) {
            String pName = name.get();
            Universe universe = loadGameFile(pName);
            if (universe != null) {
                startLoadGame(universe);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Name");
                alert.setHeaderText("Invalid player name");
                alert.setContentText("Save file does not exist.");
                alert.showAndWait();
            }
        }
    }

    private void updateInventory() {
        inventoryListView.getItems().clear();
        inventoryListView.getItems().addAll(player.getInventory().getInventory());
    }

    @FXML
    private void inventoryInteraction(MouseEvent event) {
        Item item = inventoryListView.getSelectionModel().getSelectedItem();
        if (item == null) {
        } else if (item instanceof Weapon) {
            try {
                player.equipWeapon(item.getName());
            } catch (ItemException | YouDontHaveThatException ex) {
                System.out.println("error");
            }
        } else if (item instanceof Armor) {
            try {
                player.equipArmor(item.getName());
            } catch (ItemException | YouDontHaveThatException ex) {
                System.out.println("error");
            }
        } else if (item instanceof Item && (item.getName().equalsIgnoreCase("Medicine") || item.getName().equalsIgnoreCase("Elixir"))) {
            if (player.getHealthPoints() == player.getMaxHealth()) {
                System.out.println("Your health is already full");
            } else {
                UseItem.useItem(player, item.getName());
            }
        } else {
            System.out.println("That can't be used");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> fillPlayer());
        if (arg != null) {
            updateCommands((String) arg, (Universe) o);
        }
        Platform.runLater(() -> updateInventory());
        commandField.setVisible(false);
    }

    private void fillPlayer() {
        playerName.setText(player.getName());

        if (player.getHealthPoints() < 10) {
            playerHP.setText(String.valueOf(player.getHealthPoints()));
            playerHP.setTextFill(Color.web("#e00606"));
            timeLine.play();
        } else {
            playerHP.setText(String.valueOf(player.getHealthPoints()));
            playerHP.setTextFill(Color.web("#000000"));
            if (timeLine != null) {
                timeLine.stop();
                playerHP.setVisible(true);
            }
        }
        playerGold.setText(String.valueOf(player.getGold()));
        playerAttack.setText(String.valueOf(player.getCalcAttack()));
        playerDefense.setText(String.valueOf(player.getCalcDefense()));
        playerEquipment.setText(player.getEquipment());
    }

    private boolean validateName(String name) {
        new File("C:\\Voyager\\").mkdir();
        if (name == null || name.equals("")) {
            return false;
        } else if (name.matches("[a-zA-Z]+")) {
            File fileCheck = new File("C:\\Voyager\\" + name + ".dat");
            if (fileCheck.exists()) {
                System.out.println("That name is taken.");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private Player createPlayer(String pName) {
        try {
            Inventory inventory = new Inventory();
            inventory.addObserver(this);
            Player player = new Player(pName, 0, 1, 30, 50, inventory);
            player.addObserver(this);
            return player;
        } catch (CharacterException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void startNewGame(Player player) {
        if (thread != null) {
            thread.stop();
        }
        Universe universe = new Universe(player);
        universe.addObserver(this);
        universe.setGameState("room");
        engine = new GameEngine();
        thread = new Thread(() -> {
            StringBufferInputStream s = new StringBufferInputStream("");
            System.setIn(s);
            engine.guiStart(universe);
        });
        thread.start();
    }

    private Universe loadGameFile(String pName) {
        String fileName = "C:\\Voyager\\" + pName + ".dat";
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e2) {
            System.out.println("That save game does not exist");
            return null;
        }
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(fileStream);
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
        try {
            Universe universe = (Universe) inputStream.readObject();
            universe.addObserver(this);
            player = universe.getPlayer();
            player.getInventory().addObserver(this);
            player.addObserver(this);
            player.setGold(player.getGold());
            return universe;
        } catch (ClassNotFoundException | IOException | CharacterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startLoadGame(Universe universe) {
        if (thread != null) {
            thread.stop();
        }
        engine = new GameEngine();
        thread = new Thread(() -> {
            StringBufferInputStream s = new StringBufferInputStream("");
            System.setIn(s);
            engine.guiStart(universe);
        });
        thread.start();
    }

    @FXML
    private void executeCommand(MouseEvent event) {
        String command = commandListView.getSelectionModel().getSelectedItem();
        sendText(command);
    }

    private void updateCommands(String arg, Universe universe) {
        switch (arg) {
            case "room":
                fillWithRooms(universe);
                break;
            case "fight":
                fillWithFight();
                break;
            case "puzzle":
                fillWithPuzzle();
                break;
            case "shop":
                Platform.runLater(() -> shopScene());
                break;
        }
    }

    private void fillWithRooms(Universe universe) {
        List<String> commands = universe.getCurrentRoom().getCommands();
        Platform.runLater(() -> fillCommandView(commands));
    }

    private void fillWithFight() {
        List<String> commands = new ArrayList<>();
        commands.add("Attack");
        commands.add("Defend");
        commands.add("Retreat");
        Platform.runLater(() -> fillCommandView(commands));
    }

    private void fillWithPuzzle() {
        List<String> commands = new ArrayList<>();
        commands.add("Give Up");
        commands.add("Get Hint");
        Platform.runLater(() -> fillCommandView(commands));
        Platform.runLater(() -> commandField.setVisible(true));
    }

    private void shopScene() {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("ShopScene.fxml"));
            Parent parent = (Pane) root.load();
            ShopSceneController controller = root.<ShopSceneController>getController();
            controller.init(player);
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // So you have to close shop before resuming game
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCommandView(List<String> commands) {
        commandListView.getItems().clear();
        commandListView.getItems().addAll(commands);
    }
}
