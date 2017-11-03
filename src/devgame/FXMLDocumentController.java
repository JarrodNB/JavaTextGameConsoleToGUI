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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable, Observer {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField commandField;

    private PipedOutputStream outputStream;

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
    @FXML
    private MenuButton fileMenu;
    @FXML
    private Button unequipWeaponButton;
    @FXML
    private Button unequipArmorButton;
    @FXML
    private AnchorPane pane;

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
        new File("C:\\Voyager\\").mkdir();
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
        if (command == null) {
            return;
        }
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
        Alert alert = new Alert(AlertType.ERROR);
        if (name.isPresent()) {
            String pName = name.get();
            if (validateName(pName, alert)) {
                player = createPlayer(pName);
                startNewGame(player);
            } else {
                alert.setTitle("Invalid Name");
                alert.setHeaderText("Invalid player name");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void loadGame(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("C:\\Voyager\\"));
        File file = chooser.showOpenDialog(playerAttack.getScene().getWindow());
        if (file != null) {
            if (file.getName().endsWith(".dat")) {
                startLoadGame(loadGameFile(file));
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid File");
                alert.setContentText("File must be a .dat file created by this game.");
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
        } else if (event.getButton() == MouseButton.SECONDARY) {
            System.out.println(item.getDescription());
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
            timeLine.play();
        } else {
            playerHP.setText(String.valueOf(player.getHealthPoints()));
            if (timeLine != null) {
                timeLine.stop();
                playerHP.setVisible(true);
            }
        }
        playerGold.setText(String.valueOf(player.getGold()));
        playerAttack.setText(String.valueOf(player.getCalcAttack()));
        playerDefense.setText(String.valueOf(player.getCalcDefense()));
        playerEquipment.setText(player.getEquipment());
        if (player.getCurrentWeapon() != null) {
            unequipWeaponButton.setVisible(true);
        } else {
            unequipWeaponButton.setVisible(false);
        }
        if (player.getCurrentArmor() != null) {
            unequipArmorButton.setVisible(true);
        } else {
            unequipArmorButton.setVisible(false);
        }
    }

    private boolean validateName(String name, Alert alert) {

        if (name == null || name.equals("")) {
            alert.setContentText("Name may not be blank.");
            return false;
        } else if (name.matches("[a-zA-Z ]+")) {
            File fileCheck = new File("C:\\Voyager\\" + name + ".dat");
            if (fileCheck.exists()) {
                alert.setContentText("Player already exists.");
                return false;
            }
            return true;
        } else {
            alert.setContentText("Please use only letters.");
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

    private Universe loadGameFile(File file) {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillCommandView(List<String> commands) {
        commandListView.getItems().clear();
        commandListView.getItems().addAll(commands);
    }

    @FXML
    private void saveGame(ActionEvent event) {
        FileOutputStream fileOut = null;
        if (thread.isAlive()) {
            Universe universe = engine.getUniverse();
            try {
                fileOut = new FileOutputStream("C:\\Voyager\\" + universe.getPlayer().getName() + ".dat");
            } catch (FileNotFoundException e) {
            }
            ObjectOutputStream objectOut = null;
            try {
                objectOut = new ObjectOutputStream(fileOut);
            } catch (IOException e) {
            }
            try {
                objectOut.writeObject(universe);
                System.out.println("Your game has been saved!");
            } catch (IOException e) {
            }
        }
    }

    @FXML
    private void unequipWeapon(ActionEvent event) throws ItemException {
        if (player != null) {
            player.unequipWeapon();
        }
    }

    @FXML
    private void unequipArmor(ActionEvent event) throws ItemException {
        if (player != null) {
            player.unequipArmor();
        }
    }
}
