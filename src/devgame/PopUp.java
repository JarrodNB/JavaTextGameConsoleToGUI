package devgame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {
    
    private PopUp(){}
    
    public static void launch(String message)
    {
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        Button button = new Button("OK");
        Label label = new Label(message);
        label.setWrapText(true);
        HBox topBox = new HBox();
        HBox bottomBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        bottomBox.setAlignment(Pos.CENTER);
        topBox.getChildren().add(label);
        bottomBox.getChildren().add(button);
        pane.setTop(topBox);
        pane.setBottom(bottomBox);
        button.setOnAction(e -> stage.close());
        Scene scene = new Scene(pane, message.length() * 7, 75);
        stage.setScene(scene);
        stage.setTitle("Warning");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
