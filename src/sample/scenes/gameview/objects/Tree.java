package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;
import sample.scenes.gameview.objects.core.ActiveObject;
import sample.scenes.gameview.objects.core.MapObject;

public class Tree extends MapObject implements ActiveObject {
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\tree.png";

    public Tree(int x, int y){
        super(x, y);
    }

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
        newScore += 5;
        GameFlow.Action.setScore(newScore);
        return false;
    }

}
