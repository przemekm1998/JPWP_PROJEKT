package sample.scenes.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.scenes.SceneElement;

public class ListOfInstructions implements SceneElement {
    public static Label label = new Label("List of instructions");

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
//            commands.add(Directions.LEFT);
            String listOfWords = label.getText();
            int index= listOfWords.lastIndexOf("\n");
            label.setText(listOfWords.substring(0, index));
        });

        GridPane listOfInstructions = new GridPane();
        listOfInstructions.setAlignment(Pos.TOP_CENTER);
        listOfInstructions.setPrefWidth(getWidth());
        listOfInstructions.add(label, 0, 1);
        listOfInstructions.add(delete, 0, 0);

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
