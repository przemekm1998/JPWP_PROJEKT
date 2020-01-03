package sample.scenes.gameview.objects;

import sample.scenes.gameview.objects.core.MapObject;

/**
 * The type Grass.
 */
public class Grass extends MapObject {
    private static final String IMG_PATH = "..\\JPWP_PROJEKT1\\src\\img\\grass.png";

    /**
     * Instantiates a new Grass.
     *
     * @param x the x
     * @param y the y
     */
    public Grass(int x, int y){
        super(x, y);
    }

    /**
     * Instantiates a new Grass.
     */
    public Grass(){
        super();
    }

    @Override
    protected String getImgPath() {
        return IMG_PATH;
    }
}
