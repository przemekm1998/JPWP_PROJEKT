package sample.scenes.gameview.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.InfoBox;
import sample.Main;
import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.controlling.Directions;
import sample.scenes.interfaces.SceneElement;

import java.util.List;

public class BottomPane implements SceneElement {
    @Override
    public Pane createWindow() {
        Button menu = new Button("BACK TO MENU");
        menu.setMinWidth(100);
        menu.setOnAction(e -> {
            Main.mainWindow.close();
            Main.mainWindow.setScene(Main.menu);
            try {
                new Main().start(Main.mainWindow);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPrefHeight(getHeight());

        pane.add(menu, 0, 0);

        return pane;
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
