package sample.scenes.gameview.objects;

public class Stone extends MapObject {
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\stone.png";

    public Stone(int x, int y){
        super(x, y);
    }

    public Stone(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }
}
