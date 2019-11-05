package sample.scenes.gameview.objects;

import sample.scenes.GameView;
import sample.scenes.gameview.GameFlow;

public class Finish extends MapObject implements ActiveObject{
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\finish.png";

    public Finish(int x, int y){
        super(x, y);
    }

    public Finish(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    @Override
    public boolean performAction() {
        GameFlow.Action.gameWon = true;
        return true;
    }
}
