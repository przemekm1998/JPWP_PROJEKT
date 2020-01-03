package sample.scenes.gameview.objects;

import sample.scenes.gameview.objects.core.MapObject;

/**
 * The type Player.
 */
public class Player extends MapObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\player.png";

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }

    /**
     * Instantiates a new Player.
     *
     * @param posX the pos x
     * @param posY the pos y
     */
    public Player(int posX, int posY) {
        super(posX, posY);;
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {
        super();
    }
}
