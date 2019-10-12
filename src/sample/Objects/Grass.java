package sample.Objects;

public class Grass extends MapObject {
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT\\src\\img\\grass.png";

    public Grass(int x, int y){
        super(x, y);
    }

    public Grass(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }
}
