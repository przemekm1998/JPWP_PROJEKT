package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.scenes.GameView;
import sample.scenes.MenuView;
import sample.scenes.gameview.controlling.Directions;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static Stage mainWindow;
    public static Scene menu, game;
    public static List<Directions> commands = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainWindow = primaryStage;

        mainWindow.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        GameView gameView = new GameView();

        menu = MenuView.createMenu(mainWindow);
        game = gameView.createWindow();

//        game.setOnKeyPressed(e -> {
//            Player player = GameMap.playerObject;
//            Directions dir = null;
//            GridPane gameMap = GameView.gameMap;
//
//            if(e.getCode() == KeyCode.W) {
//                dir = Directions.UP;
//            }
//            if(e.getCode() == KeyCode.S) {
//                dir = Directions.DOWN;
//            }
//            if(e.getCode() == KeyCode.A) {
//                dir = Directions.LEFT;
//            }
//            if(e.getCode() == KeyCode.D) {
//                dir = Directions.RIGHT;
//            }
//
//            switch (dir) {
//                case UP:
//                    int y = player.getPosY();
//                    y -= 1;
//                    player.setPosY(y);
//                    break;
//                case LEFT:
//                    int x = player.getPosX();
//                    x -= 1;
//                    player.setPosX(x);
//                    break;
//                case RIGHT:
//                    int x2 = player.getPosX();
//                    x2 += 1;
//                    player.setPosX(x2);
//                    break;
//                case DOWN:
//                    int y2 = player.getPosY();
//                    y2 += 1;
//                    player.setPosY(y2);
//                    break;
//            }
//            try{
//                if(player.getPosX() > GameMap.MAX_WIDTH || player.getPosY() > GameMap.MAX_HEIGHT){
//                    throw new IllegalArgumentException();
//                }
//                gameMap.setRowIndex(player.getImgView(), player.getPosY());
//                gameMap.setColumnIndex(player.getImgView(), player.getPosX());
//            } catch(IllegalArgumentException ex) {
//                InfoBox.display("Warning", "Out of map! You've Lost!");
//                mainWindow.setScene(menu);
//            }
//
//        });

        mainWindow.setTitle("Forest Paths");
        mainWindow.setScene(menu);
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram(){
        boolean answer = ConfirmBox.display("Exit Confirmation",
                "Are you sure you want to exit?");
        if(answer) {
            mainWindow.close();
        }
    }
}
