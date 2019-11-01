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

        // TODO -- clean this
        menu = MenuView.createMenu(mainWindow);
        game = gameView.createWindow();

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
