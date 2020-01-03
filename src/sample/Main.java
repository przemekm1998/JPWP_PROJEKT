package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.scenes.MenuView;

/**
 * The type Main.
 */
public class Main extends Application {

    /**
     * The constant mainWindow.
     */
    public static Stage mainWindow;
    /**
     * The constant menu.
     */
    public static Scene menu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainWindow = primaryStage;

        mainWindow.setResizable(false);

        mainWindow.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        menu = MenuView.createMenu(mainWindow);


        menu.getStylesheets().add("sample/Style.css");


        mainWindow.setTitle("Forest Paths");
        mainWindow.setScene(menu);
        mainWindow.show();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Exit Confirmation",
                "Are you sure you want to exit?");
        if (answer) {
            mainWindow.close();
        }
    }
}
