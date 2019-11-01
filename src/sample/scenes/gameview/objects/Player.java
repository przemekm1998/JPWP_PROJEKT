package sample.scenes.gameview.objects;

public class Player extends MapObject {
    private static final String IMG_PATH = "C:\\Users\\przem\\IdeaProjects\\JPWP_PROJEKT1\\src\\img\\player.png";

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    public Player(int posX, int posY) {
        super(posX, posY);;
    }

    public Player() {
        super();
    }
}
