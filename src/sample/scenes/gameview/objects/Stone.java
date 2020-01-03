package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;

/**
 * The type Stone.
 */
public class Stone extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\stone.png";

    /**
     * Instantiates a new Stone.
     *
     * @param x the x
     * @param y the y
     */
    public Stone(int x, int y) {
        super(x, y);
    }

    /**
     * Instantiates a new Stone.
     */
    public Stone() {
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        GameFlow.Action.setGameWon(false);
        return true;
    }
}
