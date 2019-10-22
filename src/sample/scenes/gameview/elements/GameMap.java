package sample.scenes.gameview.elements;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import sample.scenes.gameview.objects.Grass;
import sample.scenes.gameview.objects.MapObject;
import sample.scenes.gameview.objects.Player;
import sample.scenes.interfaces.SceneElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GameMap implements SceneElement {
    public static Player playerObject;
    public static List<MapObject> list = new ArrayList<>();

    public static final int MAX_WIDTH = 6;
    public static final int MAX_HEIGHT = 6;

    @Override
    public Pane createWindow() {
        playerObject = new Player();
        Grass grassObject = new Grass();

        return generateMap(playerObject, grassObject);
    }

    @NotNull
    private GridPane generateMap(Player playerObject, Grass grassObject) {

        Function<GridPane, GridPane> mapFill = (gameMap) -> {
            for(int width = 0; width <= MAX_WIDTH; width++) {
                for(int height = 0; height <= MAX_HEIGHT; height++) {
                    gameMap.add(new ImageView(grassObject.getImg()), width, height, 1, 1);
                    list.add(new Grass(width, height));
                }
            }

            playerObject.setPosX(MAX_WIDTH/2);
            playerObject.setPosY(MAX_HEIGHT);

            gameMap.add(playerObject.getImgView(), playerObject.getPosX(), playerObject.getPosY());

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
