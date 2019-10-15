package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import net.miginfocom.layout.Grid;
import sample.scenes.gameview.GameMap;
import sample.scenes.gameview.Instructions;
import sample.scenes.gameview.ListOfInstructions;
import sample.scenes.gameview.Score;

import java.util.Random;

public class GameView implements SceneView{
    // Rand for food
    static Random rand = new Random();

    static boolean gameOver = false;

    public static GridPane listOfInstructions;

    public static GridPane gameMap;

    @Override
    public Scene createWindow(){

        GameMap mapView = new GameMap();
        Instructions instructions = new Instructions();
        Score scoreView = new Score();
        ListOfInstructions list = new ListOfInstructions();

        gameMap = (GridPane) mapView.createWindow();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mapView.createWindow());
        borderPane.setLeft(instructions.createWindow());
        borderPane.setTop(scoreView.createWindow());
        borderPane.setRight(list.createWindow());

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
