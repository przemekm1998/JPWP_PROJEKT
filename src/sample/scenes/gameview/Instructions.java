package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.util.ArrayList;
import java.util.List;

public class Instructions implements SceneElement {
    private List<Directions> commands = new ArrayList<>();

    @Override
    public Pane createWindow() {
        Button top = new Button("UP");
        top.setMinWidth(100);
        top.setOnAction(e -> {
            commands.add(Directions.UP);
            System.out.println("Added top");
        });

        Button left = new Button("LEFT");
        left.setMinWidth(100);
        left.setOnAction(e -> {
            commands.add(Directions.LEFT);
            System.out.println("Added left");
        });

        Button right = new Button("RIGHT");
        right.setMinWidth(100);
        right.setOnAction(e -> {
            commands.add(Directions.RIGHT);
            System.out.println("Added right");
        });

        Button down = new Button("DOWN");
        down.setMinWidth(100);
        down.setOnAction(e -> {
            commands.add(Directions.DOWN);
            System.out.println("Added down");
        });

        Button start = new Button("START");
        start.setMinWidth(100);
        start.setOnAction(e -> {
            for (Directions dir : commands) {
                System.out.println("X: " + GameView.Player.x);
                System.out.println("Y: " + GameView.Player.y);
                GameView.game.getChildren().remove(GameMap.player1);
                switch (dir) {
                    case UP:
                        GameView.Player.y--;
                        break;
                    case LEFT:
                        GameView.Player.x--;
                        break;
                    case RIGHT:
                        GameView.Player.x++;
                        break;
                    case DOWN:
                        GameView.Player.y++;
                        break;
                }
                GameView.game.add(GameMap.player1, GameView.Player.x, GameView.Player.y);
            }
        });

        Label label = new Label("Available instructions");

        GridPane instructions = new GridPane();
        instructions.setVgap(10);
        instructions.setAlignment(Pos.CENTER);
        instructions.setPrefWidth(200);
        instructions.add(label, 0, 0);
        instructions.add(top, 0, 1);
        instructions.add(left, 0, 2);
        instructions.add(right, 0, 3);
        instructions.add(down, 0, 4);
        instructions.add(start, 0, 5);

        return instructions;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}
