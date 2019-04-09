package sample.Sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fondo {

    private Image image;
    private int positionX;
    private int positionY;
    private double width;
    private double height;

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setImage(){
        Image i= new Image("sample\\Images\\fondo.png");
        setImage(i);
    }

    public void setImage(Image img){
        image= img;
        width = img.getWidth();
        height = img.getHeight();
    }
    public void setPosition(int x, int y)
    {
        positionX = x;
        positionY = y;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }
    public void clear(GraphicsContext gc) {
        gc.clearRect(positionX,positionY, width, height);
    }
}
