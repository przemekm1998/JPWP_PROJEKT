package sample.scenes;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Menu view.
 */
public class MenuView {
    /**
     * The constant gameView.
     */
    public static GameView gameView = new GameView();
    /**
     * The constant game.
     */
    public static Scene game;

    /**
     * The constant choiceBox.
     */
    public static ChoiceBox<String> choiceBox = new ChoiceBox<>();

    /**
     * Create menu scene.
     *
     * @param primaryStage the primary stage
     * @return the scene
     */
    public static Scene createMenu(Stage primaryStage) {
        Stage window = new Stage();

        window.setMinWidth(250);
        VBox menuOptions = new VBox(20);
        menuOptions.setAlignment(Pos.CENTER);

        Label label = new Label("Welcome to Forest Paths!");
        Label label1 = new Label("Select difficulty level");

        if (choiceBox.getItems().isEmpty()) {
            choiceBox.getItems().addAll("Easy", "Medium", "Hard");
            choiceBox.setValue("Easy");
        }

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            Platform.runLater(() -> {
                game = gameView.createWindow();
                game.getStylesheets().add("sample/Style.css");
                primaryStage.setScene(game);
            });
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        menuOptions.getChildren().addAll(label, label1, choiceBox, startButton,
                exitButton);

        return new Scene(menuOptions, 400, 400);
    }
}
