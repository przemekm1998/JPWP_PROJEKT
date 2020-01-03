package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;

/**
 * The type Finish.
 */
public class Finish extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\finish.png";

    /**
     * Instantiates a new Finish.
     *
     * @param x the x
     * @param y the y
     */
    public Finish(int x, int y){
        super(x, y);
    }

    /**
     * Instantiates a new Finish.
     */
    public Finish(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        GameFlow.Action.setGameWon(true);
        return true;
    }
}
