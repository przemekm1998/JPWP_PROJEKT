package sample.scenes;

import javafx.scene.layout.Pane;

public interface SceneElement extends WindowSize{
    Pane createWindow();
}
