package sample.scenes.gameview.objects;

public class BonusPoints extends MapObject {
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
}
