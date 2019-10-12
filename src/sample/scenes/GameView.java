package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.scenes.gameview.GameMap;
import sample.scenes.gameview.Instructions;
import sample.scenes.gameview.Score;

import java.util.Random;

public class GameView implements SceneView{
    // Rand for food
    static Random rand = new Random();

    static boolean gameOver = false;

    public static GridPane gameMap;
    public static GridPane instructionsBox;
    public static GridPane score;

    @Override
    public Scene createWindow(){

        GameMap mapView = new GameMap();
        Instructions instructions = new Instructions();
        Score scoreView = new Score();

        gameMap = (GridPane) mapView.createWindow();
        instructionsBox = (GridPane) instructions.createWindow();
        score = (GridPane) scoreView.createWindow();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gameMap);
        borderPane.setLeft(instructionsBox);
        borderPane.setRight(score);

        return new Scene(borderPane);
    }

    @Override
    public int getWidth() {
        return 450;
    }

    @Override
    public int getHeight() {
        return 450;
    }
}
