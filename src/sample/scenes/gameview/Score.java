package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Objects.Grass;
import sample.Objects.Player;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.util.ArrayList;
import java.util.List;

public class Score implements SceneElement {

    public static Label label = new Label();
    public static int score = 0;

    public static Label getLabel() {
        return label;
    }

    public static void setLabel(int score) {
        label.setText("Score : " + score);
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

    @Override
    public Pane createWindow() {
        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            Player player = GameMap.playerObject;
            GridPane gameMap = GameView.gameMap;
            List<Directions> commandz = Instructions.commands;

            for (Directions dir : commandz) {

                gameMap.getChildren().remove(player.getImgView());

                System.out.println("X: " + player.getPosX());
                System.out.println("Y: " + player.getPosY());
                int point = Score.getScore();
                point += 1;
                Score.setScore(point);
                System.out.println("point = " + point);
                Score.setLabel(point);

                switch (dir) {
                    case UP:
                        int y = player.getPosY();
                        y -= 1;
                        player.setPosY(y);
                        break;
                    case LEFT:
                        int x = player.getPosX();
                        x -= 1;
                        player.setPosX(x);
                        break;
                    case RIGHT:
                        int x2 = player.getPosX();
                        x2 += 1;
                        player.setPosX(x2);
                        break;
                    case DOWN:
                        int y2 = player.getPosY();
                        y2 += 1;
                        player.setPosY(y2);
                        break;
                }

                for(Grass object : GameMap.list) {
                    if (object.getPosX() == player.getPosX() && object.getPosY() == player.getPosY()) {
                        System.out.println("I'm on the grass");
                    }
                }

                gameMap.add(player.getImgView(), player.getPosX(), player.getPosY());

            }
        });

        setLabel(getScore());

        GridPane score = new GridPane();
        score.setVgap(10);
        score.setAlignment(Pos.CENTER);
        score.setPrefHeight(getHeight());
        score.add(label, 0, 1);
        score.add(start, 0, 0);

        return score;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 80;
    }

}
