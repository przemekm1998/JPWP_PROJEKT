package sample.scenes.gameview.elements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.gameview.objects.Finish;
import sample.scenes.gameview.objects.MapObject;
import sample.scenes.gameview.objects.Player;
import sample.scenes.GameView;
import sample.scenes.interfaces.SceneElement;
import java.util.List;

public class Score implements SceneElement {

    @Override
    public Pane createWindow() {
        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {

            Player player = GameMap.playerObject;
            GridPane gameMap = GameView.gameMap;
            List<Directions> commands = Instructions.commands;
            List<MapObject> bonusPoints = GameMap.bonusPoints;

            if (commands.isEmpty()) {
                InfoBox.display("Warning", "No instructions to run");
            } else {
                new Thread(() -> {
                    for (Directions dir : commands) {
                        runGame(dir, player, gameMap);;
                    }
                }).start();
            }

        });


        GridPane score = new GridPane();
        score.setVgap(10);
        score.setAlignment(Pos.CENTER);
        score.setPrefHeight(getHeight());

        score.add(start, 0, 0);

        return score;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 100;
    }

    private void runGame(Directions dir, Player player, GridPane gameMap){
        boolean runGame = true;
        Finish finish = GameMap.finishObject;

        try {
            Thread.sleep(500); // Wait for 1 sec before updating the color
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Platform.runLater(() -> {
            move(dir, player, gameMap);
            gameMap.setRowIndex(player.getImgView(), player.getPosY());
            gameMap.setColumnIndex(player.getImgView(), player.getPosX());
            if(player.getPosX() == finish.getPosX() && player.getPosY() == finish.getPosY()) {
                InfoBox.display("Win", "You've won!");
            }
        });
    }

    private void move(Directions direction, MapObject object, GridPane gameMap) {

        switch (direction) {
            case UP:
                int y = object.getPosY();
                y -= 1;
                object.setPosY(y);
                break;
            case LEFT:
                int x = object.getPosX();
                x -= 1;
                object.setPosX(x);
                break;
            case RIGHT:
                int x2 = object.getPosX();
                x2 += 1;
                object.setPosX(x2);
                break;
            case DOWN:
                int y2 = object.getPosY();
                y2 += 1;
                object.setPosY(y2);
                break;
        }
    }

    private int calculateScore(int addedPoints, int points) {
        int point = points;
        point += addedPoints;
        return point;
    }

    private int checkBonusPoints(List<MapObject> bonusPoints, Player player) {
        int points = 0;

        for (MapObject object : bonusPoints) {
            if (object.getPosX() == player.getPosX() && object.getPosY() == player.getPosY()) {
                System.out.println(" +1 point ");
                points += 5;
            }
        }

        return points;
    }

}
