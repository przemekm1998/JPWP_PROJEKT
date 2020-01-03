package sample.scenes.gameview.elements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.MenuView;
import sample.scenes.gameview.objects.*;
import sample.scenes.gameview.objects.core.MapObject;
import sample.scenes.interfaces.SceneElement;

import java.util.*;

/**
 * The type Game map.
 */
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

        // Setting the amount of objects based on selected level
        int amount = getLevel();

        // Generating bonus points, random points and obstacles
        for (MapObject object : activeMapElements) {
            objectsRandomizer(object, amount, gameMap);
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

    private int getLevel(){
        String level = MenuView.choiceBox.getValue();

        int amount = 0;

        switch (level) {
            case "Easy":
                amount = 3;
                break;
            case "Medium":
                amount = 4;
                break;
            case "Hard":
                amount = 5;
                break;
            default: {
                amount = 3;
                break;
            }
        }

        return amount;
    }

    /**
     * Gets player object.
     *
     * @return the player object
     */
    public static Player getPlayerObject() {
        return playerObject;
    }

    private static void setPlayerObject(Player playerObject) {
        GameMap.playerObject = playerObject;
    }

    /**
     * Gets finish object.
     *
     * @return the finish object
     */
    public static Finish getFinishObject() {
        return finishObject;
    }

    private static void setFinishObject(Finish finishObject) {
        GameMap.finishObject = finishObject;
    }

    /**
     * Get taken coordinates map object [ ] [ ].
     *
     * @return the map object [ ] [ ]
     */
    public static MapObject[][] getTakenCoordinates() {
        return takenCoordinates;
    }

    private static void setTakenCoordinates(MapObject[][] takenCoordinates) {
        GameMap.takenCoordinates = takenCoordinates;
    }

    /**
     * Gets max width.
     *
     * @return the max width
     */
    public static int getMaxWidth() {
        return MAX_WIDTH;
    }

    /**
     * Gets max height.
     *
     * @return the max height
     */
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
