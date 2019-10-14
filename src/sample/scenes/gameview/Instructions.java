package sample.scenes.gameview;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Objects.Grass;
import sample.Objects.Player;
import sample.scenes.GameView;
import sample.scenes.SceneElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Instructions implements SceneElement {
    public static List<Directions> commands = new ArrayList<>();

    @Override
    public Pane createWindow() {

        Label listOfCommands = ListOfInstructions.label;

        Button top = new Button("UP");
        top.setMinWidth(100);
        top.setOnAction(e -> {
            commands.add(Directions.UP);
            listOfCommands.setText(listOfCommands.getText() + "\n UP");
            System.out.println("Added top");
        });

        Button left = new Button("LEFT");
        left.setMinWidth(100);
        left.setOnAction(e -> {
            commands.add(Directions.LEFT);
            listOfCommands.setText(listOfCommands.getText() + "\n LEFT");
            System.out.println("Added left");
        });

        Button right = new Button("RIGHT");
        right.setMinWidth(100);
        right.setOnAction(e -> {
            commands.add(Directions.RIGHT);
            listOfCommands.setText(listOfCommands.getText() + "\n RIGHT");
            System.out.println("Added right");
        });

        Button down = new Button("DOWN");
        down.setMinWidth(100);
        down.setOnAction(e -> {
            commands.add(Directions.DOWN);
            listOfCommands.setText(listOfCommands.getText() + "\n DOWN");
            System.out.println("Added down");
        });

        Label label = new Label("Available instructions");

        GridPane instructions = new GridPane();
        instructions.setVgap(10);
        instructions.setAlignment(Pos.CENTER);
        instructions.setPrefWidth(getWidth());
        instructions.add(label, 0, 0);
        instructions.add(top, 0, 1);
        instructions.add(left, 0, 2);
        instructions.add(right, 0, 3);
        instructions.add(down, 0, 4);

        return instructions;
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
