package sample.scenes.gameview.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.scenes.interfaces.SceneElement;
import sample.scenes.gameview.controlling.Directions;

import java.util.List;

/**
 * The type List of instructions.
 */
public class ListOfInstructions implements SceneElement {
    /**
     * The constant label.
     */
    public static Label label;

    /**
     * Gets label text.
     *
     * @return the label text
     */
    public static String getLabelText() {
        return label.getText();
    }

    /**
     * Sets label text.
     *
     * @param text the text
     */
    public static void setLabelText(String text) {
        ListOfInstructions.label.setText(text);
    }

    @Override
    public Pane createWindow() {
        label = new Label("");

        Button delete = new Button("DELETE LAST INSTRUCTION");
        delete.setMinWidth(100);
        delete.setOnAction(e -> {

            List<Directions> commands = Instructions.getCommands();

            if (commands.size() > 0) {
                String listOfInstructions = getLabelText();
                deleteInstruction(listOfInstructions, commands);
            }
            else {
                InfoBox.display("Warning", "No instruction to delete");
            }

        });

        Label list = new Label("List of Instructions");

        ScrollPane sp = new ScrollPane(label);

        GridPane listOfInstructions = new GridPane();
        listOfInstructions.setAlignment(Pos.TOP_CENTER);
        listOfInstructions.setPrefWidth(getWidth());
        listOfInstructions.setVgap(10);

        listOfInstructions.add(list, 0, 0);
        listOfInstructions.add(sp, 0, 2);
        listOfInstructions.add(delete, 0, 1);

        return listOfInstructions;
    }

    /**
     * Delete instruction.
     *
     * @param listOfWords the list of words
     * @param commands    the commands
     */
    public static void deleteInstruction(String listOfWords, List<Directions> commands) {
        System.out.println(commands.get(commands.size() - 1));

        int index= listOfWords.lastIndexOf("MOVE");
        setLabelText(listOfWords.substring(0, index));
        commands.remove(commands.size() - 1);
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
