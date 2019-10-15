package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Objects.Grass;
import sample.Objects.MapObject;
import sample.Objects.Player;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.util.ArrayList;
import java.util.List;

public class Score implements SceneElement {

    public static int score = 0;
    public static Label scoreLabel = new Label("Score : " + score);

    public static int steps = 0;
    public static Label stepsLabel = new Label("Steps : " + steps);

    public static Label getscoreLabel() {
        return scoreLabel;
    }

    public static void setscoreLabel(int score) {
        scoreLabel.setText("Score : " + score);
    }

    public static Label getstepsLabel() {
        return stepsLabel;
    }

    public static void setsstepsLabel(int steps) {
        stepsLabel.setText("Steps : " + steps);
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

    public static int getSteps() {
        return score;
    }

    public static void setSteps(int steps) {
        Score.steps = score;
    }

    @Override
    public Pane createWindow() {
        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {

            Player player = GameMap.playerObject;
            GridPane gameMap = GameView.gameMap;
            List<Directions> commands = Instructions.commands;
            List<MapObject> bonusPoints = GameMap.list;

            for (Directions dir : commands) {
                move(dir, player, gameMap);

                int newSteps = calculateScore(1, getSteps());
                setSteps(newSteps);
                setsstepsLabel(getSteps());

                int bonus = checkBonusPoints(bonusPoints, player);
                int newPoints = calculateScore(bonus, getScore());
                setScore(newPoints);
                setscoreLabel(getScore());
            }
        });

        GridPane score = new GridPane();
        score.setVgap(10);
        score.setAlignment(Pos.CENTER);
        score.setPrefHeight(getHeight());

        Label score1 = getscoreLabel();
        Label steps1 = getstepsLabel();

        score.add(start, 0, 0);
        score.add(score1, 0, 1);
        score.add(steps1, 0, 2);

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

        gameMap.getChildren().remove(object.getImgView());
        gameMap.add(object.getImgView(), object.getPosX(), object.getPosY());
    }

    private int calculateScore(int addedPoints, int points) {
        int point = points;
        point += addedPoints;
        return point;
    }

    private int checkBonusPoints(List<MapObject> bonusPoints, Player player) {
        int points = 0;

        for(MapObject object : bonusPoints) {
            if (object.getPosX() == player.getPosX() && object.getPosY() == player.getPosY()) {
                System.out.println(" +1 point ");
                points += 5;
            }
        }

        return points;
    }

}
