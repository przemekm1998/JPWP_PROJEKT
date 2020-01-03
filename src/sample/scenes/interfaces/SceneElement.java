package sample.scenes.interfaces;

import javafx.scene.layout.Pane;

/**
 * The interface Scene element.
 */
public interface SceneElement extends WindowSize {
    /**
     * Create window pane.
     *
     * @return the pane
     */
    Pane createWindow();
}
