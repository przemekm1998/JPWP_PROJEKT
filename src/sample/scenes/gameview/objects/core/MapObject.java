package sample.scenes.gameview.objects.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

/**
 * The type Map object.
 */
public abstract class MapObject {

    private ImageView imgView;
    private Image img;

    private int posX = 0;
    private int posY = 0;

    /**
     * Gets img path.
     *
     * @return the img path
     */
    protected abstract String getImgPath();

    /**
     * Gets img view.
     *
     * @return the img view
     */
    public ImageView getImgView() {
        return imgView;
    }

    private void setImgView() {
        Image imgToView = getImg();
        imgView = new ImageView(imgToView);
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public Image getImg() {
        try{
            img = new Image(new FileInputStream(getImgPath()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Gets pos x.
     *
     * @return the pos x
     */
    public final int getPosX() {
        return posX;
    }

    /**
     * Sets pos x.
     *
     * @param posX the pos x
     */
    public final void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Gets pos y.
     *
     * @return the pos y
     */
    public final int getPosY() {
        return posY;
    }

    /**
     * Sets pos y.
     *
     * @param posY the pos y
     */
    public final void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Instantiates a new Map object.
     *
     * @param posX the pos x
     * @param posY the pos y
     */
    protected MapObject(int posX, int posY) {
        setImgView();
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Instantiates a new Map object.
     */
    protected MapObject() {
        setImgView();
    }
}
