package sample.scenes.gameview.elements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.gameview.objects.*;
import sample.scenes.gameview.objects.core.MapObject;
import sample.scenes.interfaces.SceneElement;

import java.util.*;

public class GameMap implements SceneElement {

    // map boundaries
    private static final int MAX_WIDTH = 6;
    private static final int MAX_HEIGHT = 6;

    private static Player playerObject;
    private static Finish finishObject;

    // array of filled coordinates by objects
    private static MapObject[][] takenCoordinates = null;

    @Override
    public Pane createWindow() {
        setTakenCoordinates(new MapObject[MAX_HEIGHT + 1][MAX_WIDTH + 1]);

        setPlayerObject(new Player(MAX_WIDTH / 2, MAX_HEIGHT));
        takenCoordinates[playerObject.getPosY()][playerObject.getPosX()] = playerObject;

        setFinishObject(new Finish(MAX_WIDTH / 2, 0));
        takenCoordinates[finishObject.getPosY()][finishObject.getPosX()] = finishObject;

        List<MapObject> activeMapElements = Arrays.asList(
                new Stone(),
                new Tree(),
                new BonusPoints()
        );

        return generateMap(activeMapElements);
    }

    private GridPane generateMap(List<MapObject> activeMapElements) {

        // GameMap settings
        GridPane gameMap = new GridPane();

        gameMap.setAlignment(Pos.CENTER);
        gameMap.setPrefWidth(getWidth());
        gameMap.setPrefHeight(getHeight());

        mapFill(gameMap, activeMapElements);

        return gameMap;
    }

    // Filling the whole map with objects
    private void mapFill(GridPane gameMap, List<MapObject> activeMapElements) {

        // Filling the map with the grass
        grassFill(gameMap);

        // Generating bonus points, random points and obstacles
        for (MapObject object : activeMapElements) {
            objectsRandomizer(object, 3, gameMap);
        }

        // Adding player and finish object
        Platform.runLater(() -> {
            gameMap.add(playerObject.getImgView(), playerObject.getPosX(), playerObject.getPosY());
            gameMap.add(finishObject.getImgView(), finishObject.getPosX(), finishObject.getPosY());
        });
    }

    private void grassFill(GridPane gameMap) {

        for (int width = 0; width <= MAX_WIDTH; width++) {
            for (int height = 0; height <= MAX_HEIGHT; height++) {

                final int finalWidth = width;
                final int finalHeight = height;

                Platform.runLater(() -> {
                    gameMap.add(new Grass().getImgView(), finalWidth, finalHeight);
                });
            }
        }
    }

    // Generating objects with random coordinates
    private void objectsRandomizer(MapObject objectToGenerate, int amount, GridPane gameMap) {

        for (int i = 0; i < amount; i++) {
            Map<String, Integer> randomCords = cordsRandomizer();

            final int posX = randomCords.get("X");
            final int posY = randomCords.get("Y");

            takenCoordinates[posY][posX] = objectToGenerate;

            Platform.runLater(() -> {
                gameMap.add(new ImageView(objectToGenerate.getImg()), posX, posY);
            });
        }
    }

    // Generating a pair of random coordinates
    private Map<String, Integer> cordsRandomizer() {
        Map<String, Integer> randomCords = new HashMap<>();

        Random rand = new Random();
        int posX, posY;
        boolean duplicate;

        do {
            duplicate = false;
            posY = rand.nextInt(((MAX_HEIGHT - 1) - 1) + 1) + 1;
            posX = rand.nextInt(((MAX_WIDTH - 1) - 1) + 1) + 1;

            // Check if coordinates are not already taken
            if (takenCoordinates[posY][posX] != null) {
                duplicate = true;
            }
        } while (duplicate);

        randomCords.put("X", posX);
        randomCords.put("Y", posY);

        return randomCords;
    }

    public static Player getPlayerObject() {
        return playerObject;
    }

    private static void setPlayerObject(Player playerObject) {
        GameMap.playerObject = playerObject;
    }

    public static Finish getFinishObject() {
        return finishObject;
    }

    private static void setFinishObject(Finish finishObject) {
        GameMap.finishObject = finishObject;
    }

    public static MapObject[][] getTakenCoordinates() {
        return takenCoordinates;
    }

    private static void setTakenCoordinates(MapObject[][] takenCoordinates) {
        GameMap.takenCoordinates = takenCoordinates;
    }

    public static int getMaxWidth() {
        return MAX_WIDTH;
    }

    public static int getMaxHeight() {
        return MAX_HEIGHT;
    }

    @Override
    public int getWidth() {
        return 400;
    }

    @Override
    public int getHeight() {
        return 400;
    }
}
