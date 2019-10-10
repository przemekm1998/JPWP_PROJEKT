package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.scenes.gameview.GameMap;
import sample.scenes.gameview.Instructions;
import java.util.Random;

public class GameView implements SceneView{
    // Rand for food
    static Random rand = new Random();

    static boolean gameOver = false;



    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Player {
        public static int x = 3;
        public static int y = 6;
    }

    public static GridPane game;
    public static GridPane instructionsBox;

    @Override
    public Scene createWindow(){

        GameMap mapView = new GameMap();
        Instructions instructions = new Instructions();

        game = (GridPane)mapView.createWindow();
        instructionsBox = (GridPane)instructions.createWindow();

//        Button comeBack = new Button("Back to Menu");
//        comeBack.setOnAction(e -> primaryStage.setScene(Main.menu));

        // Grass Pane


        // Instructions


//        Canvas c = new Canvas(width*cornersize, height*cornersize);
//        GraphicsContext gc = c.getGraphicsContext2D();



        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(game);
        borderPane.setLeft(instructionsBox);

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
