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

    public static Label label;
    public static int score = 0;

    @Override
    public Pane createWindow() {
        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            System.out.println("START");
        });

        label = new Label("Score: " + score);

        GridPane score = new GridPane();
        score.setVgap(10);
        score.setAlignment(Pos.CENTER);
        score.setPrefWidth(getWidth());
        score.add(label, 0, 0);
        score.add(start, 0, 1);

        return score;
    }

    @Override
    public int getWidth() {
        return 200;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}
