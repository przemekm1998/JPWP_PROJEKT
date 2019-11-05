package sample.scenes.gameview.objects;

import javafx.application.Platform;
import sample.InfoBox;
import sample.scenes.gameview.GameFlow;

public class Stone extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\stone.png";

    public Stone(int x, int y) {
        super(x, y);
    }

    public Stone() {
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        GameFlow.Action.gameWon = false;
        return true;
    }
}
