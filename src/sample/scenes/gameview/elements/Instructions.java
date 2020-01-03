package sample.scenes.gameview.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.interfaces.SceneElement;
import sample.scenes.gameview.controlling.Directions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * The type Instructions.
 */
public class Instructions implements SceneElement {
    private static List<Directions> commands;

    @Override
    public Pane createWindow() {

        setCommands(new ArrayList<>());

        Button top = new Button("UP");
        top.setMinWidth(100);
        top.setOnAction(e -> {
            addCommand(Directions.UP);
        });

        Button left = new Button("LEFT");
        left.setMinWidth(100);
        left.setOnAction(e -> {
            addCommand(Directions.LEFT);
        });

        Button right = new Button("RIGHT");
        right.setMinWidth(100);
        right.setOnAction(e -> {
            addCommand(Directions.RIGHT);
        });

        Button down = new Button("DOWN");
        down.setMinWidth(100);
        down.setOnAction(e -> {
            addCommand(Directions.DOWN);
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

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public static List<Directions> getCommands() {
        return commands;
    }

    /**
     * Sets commands.
     *
     * @param commands the commands
     */
    public static void setCommands(List<Directions> commands) {
        Instructions.commands = commands;
    }

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    private void addCommand(Directions direction) {
        Label listOfCommands = ListOfInstructions.label;

        commands.add(direction);
        listOfCommands.setText(listOfCommands.getText() + "MOVE " + direction.toString() + " (step " + commands.size() + ")"  + "\n");
    }

}
