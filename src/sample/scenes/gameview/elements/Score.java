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
import sample.scenes.interfaces.SceneElement;

import java.util.List;

public class Score implements SceneElement {
    private static Label labelScore;
    private static Label labelSteps;

    @Override
    public Pane createWindow() {
        labelScore = new Label("Score: ");
        labelSteps = new Label("Steps: ");

        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            List<Directions> commands = Instructions.getCommands();

            if (commands.isEmpty()) {
                InfoBox.display("Warning", "No instructions to run");
            } else {
                GameFlow.runFlow();
            }
        });

        GridPane score = new GridPane();
        score.setVgap(10);
        score.setAlignment(Pos.CENTER);
        score.setPrefHeight(getHeight());

        score.add(start, 0, 0);
        score.add(getLabelScore(), 0, 1);
        score.add(getLabelSteps(), 0, 2);

        return score;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 50;
    }

    public static Label getLabelScore() {
        return labelScore;
    }

    public static void setLabelScore(int score) {
        Platform.runLater(() -> {
            Score.labelScore.setText("Score: " + score);
        });
    }

    public static Label getLabelSteps() {
        return labelSteps;
    }

    public static void setLabelSteps(int steps) {
        Platform.runLater(() -> {
            Score.labelSteps.setText("Steps: " + steps);
        });
    }
}
