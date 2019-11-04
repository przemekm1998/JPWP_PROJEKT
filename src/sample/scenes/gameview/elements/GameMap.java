package sample.scenes.gameview.elements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.gameview.objects.*;
import sample.scenes.interfaces.SceneElement;

import java.util.*;
import java.util.function.Function;

public class GameMap implements SceneElement {

    // map boundaries
    private static final int MAX_WIDTH = 6;
    private static final int MAX_HEIGHT = 6;

    private static Player playerObject;
    private static Finish finishObject;

    // array of filled coordinates by objects
    private static MapObject[][] takenCoordinates = new MapObject[MAX_HEIGHT + 1][MAX_WIDTH + 1];

    @Override
    public Pane createWindow() {
        playerObject = new Player();
        finishObject = new Finish();

        List<MapObject> activeMapElements = Arrays.asList(
                new Stone(),
                new Tree(),
                new BonusPoints()
        );

        return generateMap(activeMapElements);
    }

    private GridPane generateMap(List<MapObject> activeMapElements) {

        // setting player's starting point
        playerObject.setPosX(MAX_WIDTH / 2);
        playerObject.setPosY(MAX_HEIGHT);
        takenCoordinates[playerObject.getPosY()][playerObject.getPosX()] = playerObject;

        // setting finish point
        finishObject.setPosX(MAX_WIDTH / 2);
        finishObject.setPosY(0);
        takenCoordinates[finishObject.getPosY()][finishObject.getPosX()] = finishObject;

        Function<GridPane, GridPane> mapFill = (gameMap) -> {
            // Map fill with grass
            Grass grassObject = new Grass();

            for (int width = 0; width <= MAX_WIDTH; width++) {
                for (int height = 0; height <= MAX_HEIGHT; height++) {
                    final int finalWidth = width;
                    final int finalHeight = height;
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            gameMap.add(new ImageView(grassObject.getImg()), finalWidth, finalHeight, 1, 1);
                        }
                    });

                }
            }

            // Generating bonus points, random points and obstacles
//            for (MapObject object : activeMapElements) {
//                objectsRandomizer(object, 3, gameMap);
//            }

            Platform.runLater(new Runnable() {
                @Override public void run() {
                    gameMap.add(finishObject.getImgView(), finishObject.getPosX(), finishObject.getPosY());
                    gameMap.add(playerObject.getImgView(), playerObject.getPosX(), playerObject.getPosY());
                }
            });
//


            return gameMap;
        };

        GridPane map = mapFill.apply(new GridPane());

        map.setAlignment(Pos.CENTER);
        map.setPrefWidth(getWidth());
        map.setPrefHeight(getHeight());

        return map;
    }

    private void objectsRandomizer(MapObject objectToGenerate, int amount, GridPane gameMap) {
        Random rand = new Random();
        int posX, posY;
        boolean duplicate;

        // Generating random coordinates for active objects
        for (int i = 0; i < amount; i++) {
            do {
                duplicate = false;
                posY = rand.nextInt(((MAX_HEIGHT - 1) - 1) + 1) + 1;
                posX = rand.nextInt(((MAX_WIDTH - 1) - 1) + 1) + 1;

                // Check if coordinates are not already taken
                if (takenCoordinates[posY][posX] != null) {
                    duplicate = true;
                }
            } while (duplicate);

            takenCoordinates[posY][posX] = objectToGenerate;

            int finalPosX = posX;
            int finalPosY = posY;
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    gameMap.add(new ImageView(objectToGenerate.getImg()), finalPosX, finalPosY);
                }
            });
        }
    }

    public static Player getPlayerObject() {
        return playerObject;
    }

    public static void setPlayerObject(Player playerObject) {
        GameMap.playerObject = playerObject;
    }

    public static Finish getFinishObject() {
        return finishObject;
    }

    public static void setFinishObject(Finish finishObject) {
        GameMap.finishObject = finishObject;
    }

    public static MapObject[][] getTakenCoordinates() {
        return takenCoordinates;
    }

    public static void setTakenCoordinates(MapObject[][] takenCoordinates) {
        GameMap.takenCoordinates = takenCoordinates;
    }

    @Override
    public int getWidth() {
        return 500;
    }

    @Override
    public int getHeight() {
        return 500;
    }
}
