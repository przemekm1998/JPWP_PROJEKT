package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;

/**
 * The type Bonus points.
 */
public class BonusPoints extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\nut.png";

    /**
     * Instantiates a new Bonus points.
     */
    public BonusPoints(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        int newScore = GameFlow.Action.getScore();
        newScore += 5;
        GameFlow.Action.setScore(newScore);
        return false;
    }
}
