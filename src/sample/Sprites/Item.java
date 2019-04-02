package sample.Sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item {
    String nombre;
    private Image image;
    private int positionX;
    private int positionY;
    private double width;
    private double height;

    public Item(String nombre){
        this.nombre=nombre;
    }

    public Item(int positionx, int positionY){
        this.positionX=positionx;
        this.positionY=positionY;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public void setImage(String file){
        Image i= new Image(file);
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

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Snake s)
    {
        //System.out.println(this.getBoundary());
        return s.getBoundary().intersects( this.getBoundary() );
    }
}
