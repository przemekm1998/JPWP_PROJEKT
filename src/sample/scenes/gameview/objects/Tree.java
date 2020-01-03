package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;

import java.util.Random;

/**
 * The type Tree.
 */
public class Tree extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\tree.png";

    /**
     * Instantiates a new Tree.
     *
     * @param x the x
     * @param y the y
     */
    public Tree(int x, int y){
        super(x, y);
    }

    /**
     * Instantiates a new Tree.
     */
    public Tree(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        int newScore = GameFlow.Action.getScore();
        newScore += new Random().nextInt((7 - 2) + 1) + 2;
        GameFlow.Action.setScore(newScore);
        return false;
    }

}
