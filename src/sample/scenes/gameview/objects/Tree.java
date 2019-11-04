package sample.scenes.gameview.objects;

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
}
