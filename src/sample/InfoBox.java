package sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.scenes.MenuView;

/**
 * The type Info box.
 */
public class InfoBox {

    /**
     * Display.
     *
     * @param title   the title
     * @param message the message
     */
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button okButton = new Button("Try Again");

        okButton.setOnAction(e -> {
            window.close();
            Main.mainWindow.close();
            Platform.runLater(() -> {
                try {
                    Main.mainWindow.setScene(MenuView.game);
                    new Main().start(Main.mainWindow);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("sample/Style.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
