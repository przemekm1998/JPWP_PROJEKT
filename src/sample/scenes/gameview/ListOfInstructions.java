package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.scenes.SceneElement;

import java.util.List;

public class ListOfInstructions implements SceneElement {
    public static Label label = new Label("");

    public static Label getLabel() {
        return label;
    }

    public static void setLabel(Label label) {
        ListOfInstructions.label = label;
    }

    @Override
    public Pane createWindow() {
        Button delete = new Button("DELETE LAST INSTRUCTION");
        delete.setMinWidth(100);
        delete.setOnAction(e -> {
            List<Directions> commands = Instructions.commands;
            if (commands.size() > 0) {
                System.out.println(commands.get(commands.size() - 1));
                String listOfWords = label.getText();
                int index= listOfWords.lastIndexOf("MOVE");
                label.setText(listOfWords.substring(0, index));
                commands.remove(commands.size() - 1);
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

    @Override
    public int getWidth() {
        return 200;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
