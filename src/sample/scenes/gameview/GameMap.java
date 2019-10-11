package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import net.miginfocom.layout.Grid;
import sample.Objects.Grass;
import sample.Objects.MapObject;
import sample.Objects.Player;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GameMap implements SceneElement {
    public static ImageView player1;

    @Override
    public Pane createWindow() {
        Player playerObject = new Player();
        Grass grassObject = new Grass();

        return generateMap(playerObject, grassObject);
    }

    private GridPane generateMap(Player playerObject, Grass grassObject) {

        Function<GridPane, GridPane> mapFill = (gameMap) -> {
            for(int i = 0; i <= 6; i++) {
                for(int j = 0; j <= 6; j++) {
                    gameMap.add(new ImageView(grassObject.getImg()), i, j, 1, 1);
                }
            }
            player1 = new ImageView(playerObject.getImg());
            gameMap.add(player1, GameView.Player.x, GameView.Player.y);
            return gameMap;
        };

        GridPane map = mapFill.apply(new GridPane());

        map.setAlignment(Pos.CENTER);
        map.setPrefWidth(getWidth());
        map.setPrefHeight(getHeight());

        return map;
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
