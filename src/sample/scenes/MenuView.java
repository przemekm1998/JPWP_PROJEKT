package sample.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

public class MenuView {
    public static Scene createMenu(Stage primaryStage) {
        Stage window = new Stage();

        window.setMinWidth(250);
        VBox menuOptions = new VBox(20);
        menuOptions.setAlignment(Pos.CENTER);

        Label label = new Label("Welcome to Forest Paths!");
        Label label1 = new Label("Select difficulty level");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Easy", "Medium", "Hard");
        choiceBox.setValue("Easy");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            primaryStage.setScene(Main.game);
            System.out.println("Eluwa byku2");
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> {
            primaryStage.close();
            System.out.println("Eluwa byku");
        });

        menuOptions.getChildren().addAll(label, label1, choiceBox, startButton,
                exitButton);

        return new Scene(menuOptions, 400, 400);
    }
}
