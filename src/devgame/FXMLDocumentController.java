/*
 */
package devgame;

import Controllers.GameEngine;
import GameExceptions.CharacterException;
import GameExceptions.ItemException;
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

/**
 *
 * @author jnbcb
 */
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
    @FXML
    private Label playerName;
    @FXML
    private Label playerHP;
    @FXML
    private Label playerGold;
    @FXML
    private ListView<?> inventoryListView;

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
    }

    public void appendText(String str) {
        Platform.runLater(() -> textArea.appendText(str));
    }

    private void startGame(String choice) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                engine = new GameEngine();
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
        synchronized(GameEngine.LOCK){
            //System.out.println("In notify block");
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
}
