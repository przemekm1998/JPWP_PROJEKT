package sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button okButton = new Button("Ok z resetem");
        Button okButton2 = new Button("Ok bez resetu");

        okButton.setOnAction(e -> {
            window.close();
            Main.mainWindow.close();
            Platform.runLater( () -> {
                try {
                    new Main().start( new Stage() );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });

        okButton2.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okButton, okButton2);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
