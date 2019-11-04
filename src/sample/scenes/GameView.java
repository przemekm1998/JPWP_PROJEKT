package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.scenes.gameview.elements.GameMap;
import sample.scenes.gameview.elements.Instructions;
import sample.scenes.gameview.elements.ListOfInstructions;
import sample.scenes.gameview.elements.Score;
import sample.scenes.interfaces.SceneView;

import java.util.Random;

public class GameView implements SceneView {
    // Rand for food
    static Random rand = new Random();

    static boolean gameOver = false;

    public static GridPane gameMap;

    public static GameMap mapView = new GameMap();

    @Override
    public Scene createWindow(){

        Instructions instructions = new Instructions();
        Score scoreView = new Score();
        ListOfInstructions list = new ListOfInstructions();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mapView.createWindow());
        borderPane.setLeft(instructions.createWindow());
        borderPane.setTop(scoreView.createWindow());
        borderPane.setRight(list.createWindow());

        return new Scene(borderPane);
    }

    @Override
    public int getWidth() {
        return 1280;
    }

    @Override
    public int getHeight() {
        return 720;
    }
}
