package sample.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public abstract class MapObject {

    private Image img;

    private int posX = 0;
    private int posY = 0;

    protected abstract String getImgPath();

    private void setImg() {
        try{
            img = new Image(new FileInputStream(getImgPath()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Image getImg(){
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
        setImg();
        this.posX = posX;
        this.posY = posY;
    }

    protected MapObject() {
        setImg();
    }
}
