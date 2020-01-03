package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Confirm box.
 */
public class ConfirmBox {
    /**
     * The Answer.
     */
    static boolean answer;

    /**
     * Display boolean.
     *
     * @param title   the title
     * @param message the message
     * @return the boolean
     */
    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(200);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("sample/Style.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
