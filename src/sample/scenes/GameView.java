package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.scenes.gameview.elements.*;
import sample.scenes.interfaces.SceneView;

import java.util.Random;

public class GameView implements SceneView {
    private static GridPane gameMap;

    @Override
    public Scene createWindow(){

        Instructions instructions = new Instructions();
        Score scoreView = new Score();
        ListOfInstructions list = new ListOfInstructions();
        GameMap mapView = new GameMap();
        BottomPane pane = new BottomPane();

        setGameMap((GridPane) mapView.createWindow());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(getGameMap());
        borderPane.setLeft(instructions.createWindow());
        borderPane.setTop(scoreView.createWindow());
        borderPane.setRight(list.createWindow());
        borderPane.setBottom(pane.createWindow());

        return new Scene(borderPane);
    }

    public static GridPane getGameMap() {
        return gameMap;
    }

    public static void setGameMap(GridPane gameMap) {
        GameView.gameMap = gameMap;
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
