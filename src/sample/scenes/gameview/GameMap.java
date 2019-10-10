package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.io.FileInputStream;

public class GameMap implements SceneElement {
    public static Image img;
    public static Image player;

    public static ImageView img1;
    public static ImageView player1;

    @Override
    public Pane createWindow() {
        try{
            player = new Image(new FileInputStream("C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT\\src\\img\\next.png"));
            img = new Image(new FileInputStream("C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT\\src\\img\\grass.png"));
        } catch (Exception e){
            e.printStackTrace();
        }

        img1 = new ImageView(img);
        player1 = new ImageView(player);

        GridPane game = new GridPane();
        game.setAlignment(Pos.CENTER);
        game.setPrefWidth(getWidth());
        game.setPrefHeight(getHeight());
        for(int i = 0; i <= 6; i++) {
            for(int j = 0; j <= 6; j++) {
                game.add(new ImageView(img), i, j, 1, 1);
            }
        }

        // Add player on map
        game.add(player1, GameView.Player.x, GameView.Player.y);

        return game;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
