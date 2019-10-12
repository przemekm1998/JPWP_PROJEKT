package sample.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public abstract class MapObject {

    private ImageView imgView;
    private Image img;

    private int posX = 0;
    private int posY = 0;

    protected abstract String getImgPath();

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView() {
        Image imgToView = getImg();
        imgView = new ImageView(imgToView);
    }

    public Image getImg() {
        try{
            img = new Image(new FileInputStream(getImgPath()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }

    public final int getPosX() {
        return posX;
    }

    public final void setPosX(int posX) {
        this.posX = posX;
    }

    public final int getPosY() {
        return posY;
    }

    public final void setPosY(int posY) {
        this.posY = posY;
    }

    protected MapObject(int posX, int posY) {
        setImgView();
        this.posX = posX;
        this.posY = posY;
    }

    protected MapObject() {
        setImgView();
    }
}
