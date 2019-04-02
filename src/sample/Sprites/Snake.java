package sample.Sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Snake {

    private Image image;
    private double positionX;
    private double positionY;
    private double velocity;
    private double width;
    private double height;


    public Snake(){

    }

    public Snake(double positionX,double positionY,double vel){
        this.positionX = positionX;
        this.positionY=positionY;
        this.velocity=vel;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }



    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setImage(String file){
        Image i= new Image(file);
        setImage(i);
    }

    public void setImage(Image img){
        image= img;
        width = img.getWidth();
        height = img.getHeight();
    }
    public void setPosition(double x, double y)
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

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Snake s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }


    public void update(String direction) {
        switch (direction) {
            case "RIGHT": positionX+=velocity; break;
            case "LEFT":  positionX-=velocity; break;
            case "DOWN":  positionY+=velocity; break;
            case "UP":    positionY-=velocity; break;
            default: break;
        }
    }
}
