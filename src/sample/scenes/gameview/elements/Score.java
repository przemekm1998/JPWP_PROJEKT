package sample.scenes.gameview.elements;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.gameview.objects.Finish;
import sample.scenes.gameview.objects.MapObject;
import sample.scenes.gameview.objects.Player;
import sample.scenes.GameView;
import sample.scenes.interfaces.SceneElement;

import java.beans.EventHandler;
import java.util.List;

public class Score implements SceneElement {

    @Override
    public Pane createWindow() {
        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            Player player = GameMap.getPlayerObject();
            GridPane gameMap = GameView.gameMap;
            List<Directions> commands = Instructions.commands;
//            List<MapObject> bonusPoints = GameMap.bonusPoints;
            Finish finish = GameMap.getFinishObject();

            if (commands.isEmpty()) {
                InfoBox.display("Warning", "No instructions to run");
            } else {
                InfoBox.display("Win", "You've won!");
//                new Thread(() -> {
//                    for (Directions dir : commands) {
//                        runGame(dir, player, gameMap);;
//                    }
//                    Platform.runLater(() -> {
//                        if(player.getPosX() == finish.getPosX() && player.getPosY() == finish.getPosY()) {
//                            e.consume();
//                            InfoBox.display("Win", "You've won!");
//                        }
//                    });
//                }).start();
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
        Finish finish = GameMap.getFinishObject();

        try {
            Thread.sleep(500); // Wait for 1 sec before updating the color
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Platform.runLater(() -> {
            move(dir, player, gameMap);
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

        gameMap.setRowIndex(object.getImgView(), object.getPosY());
        gameMap.setColumnIndex(object.getImgView(), object.getPosX());
    }
}
