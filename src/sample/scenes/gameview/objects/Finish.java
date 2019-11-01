package sample.scenes.gameview.objects;

public class Finish extends MapObject {
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
}
