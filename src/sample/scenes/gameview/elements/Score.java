package sample.scenes.gameview.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.interfaces.SceneElement;

import java.util.List;

public class Score implements SceneElement {

    @Override
    public Pane createWindow() {
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
}
