package sample.scenes.gameview.objects;

import sample.scenes.gameview.GameFlow;

public class BonusPoints extends MapObject implements ActiveObject{
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\nut.png";

    public BonusPoints(int x, int y){
        super(x, y);
    }

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
