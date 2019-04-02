package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Images.Marcador;
import sample.Sprites.Item;
import sample.Sprites.Snake;

import java.util.Random;

public class Main extends Application {

    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static int alto = 600;
    static int ancho = 600;
    int puntuacion =0;
    String direccion ="LEFT";
    Random random= new Random();
    // activo y bomb 0 = true 1 = false
    int activo=0;
    int bomb = 2;
    Snake head = new Snake();
    Item fruta = new Item("fruta");
    Item bomba = new Item("bomba");
    Marcador marcador= new Marcador();

    @Override
    public void start(Stage stage) {
        stage.setTitle("SNAKE");

        Group root = new Group();
        mainScene = new Scene(root);
        stage.setScene(mainScene);

        Canvas canvas = new Canvas(alto,ancho);
        root.getChildren().add(canvas);

        head.setImage("sample\\Images\\gatilen.png");
        head.setVelocity(1);
        head.setPosition((alto/2),(ancho/2));

        prepareActionHandlers(direccion);


        graphicsContext= canvas.getGraphicsContext2D();
        head.render(graphicsContext);

        fruta.setImage("sample\\Images\\fruta.png");
        bomba.setImage("sample\\Images\\bomba.png");

        fruta.setPosition(random.nextInt(ancho),random.nextInt(alto));
        fruta.render(graphicsContext);


        new AnimationTimer(){
            long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    // activo y bomb 0 = true 1 = false
                    if (activo==1) {
                        pintarItem();
//                        bomb = random.nextInt(2);
//                        System.out.println("bomba=" + bomb);
//                        if (bomb==1) {
//                            bomba.setPosition(random.nextInt((int) (ancho - bomba.getWidth())), random.nextInt((int) (alto - bomba.getHeight())));
//                            bomba.render(graphicsContext);
//                        } else {
//                            fruta.setPosition(random.nextInt((int) (ancho - fruta.getWidth())), random.nextInt((int) (alto - fruta.getHeight())));
//                            fruta.render(graphicsContext);
//                        }
//                        activo = 0;
//                    try {
//                        System.out.println("sleep");
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    }

                    head.clear(graphicsContext);
                    head.update(direccion);
                    if (head.getPositionX() < 0 || (head.getPositionX() + head.getWidth()) > ancho || head.getPositionY() < 0 || (head.getPositionY() + head.getHeight()) > alto - 10) {
                        System.out.println("Has perdido");
                        System.exit(0);
                    }
                    head.render(graphicsContext);
                    comprobarItem(head);
//                    if (fruta.intersects(head)) {
//                        activo = 1;
//                        fruta.clear(graphicsContext);
//                        marcador.suma();
//                        System.out.println("Has comido una fruta "+activo);
//                        System.out.println(marcador.getPuntuacion());
//
//
//                    } else if (bomba.intersects(head)) {
//                        activo = 1;
//                        bomba.clear(graphicsContext);
//                        marcador.resta();
//                        System.out.println("Has comido una bomba "+activo);
//                        System.out.println(marcador.getPuntuacion());
//
//                    }
                    lastUpdate = now;
                }
            }
        }.start();
        stage.show();
    }
    public void pintarItem(){
        bomb = random.nextInt(2);
        System.out.println("bomba=" + bomb);
        if (bomb==1) {
            bomba.setPosition( random.nextInt( (int) ( ancho - bomba.getWidth() ) ), random.nextInt( (int) ( alto - bomba.getHeight() ) ) );
            bomba.render(graphicsContext);
        } else {
            fruta.setPosition(random.nextInt( (int) ( ancho - fruta.getWidth() ) ), random.nextInt( (int) ( alto - fruta.getHeight() ) ) );
            fruta.render(graphicsContext);
        }
        activo = 0;
    }
    public void comprobarItem(Snake head){
        if (fruta.intersects(head)) {
            activo = 1;
            fruta.clear(graphicsContext);
            marcador.suma();
            System.out.println("Has comido una fruta "+activo);
            System.out.println(marcador.getPuntuacion());


        } else if (bomba.intersects(head)) {
            activo = 1;
            bomba.clear(graphicsContext);
            marcador.resta();
            System.out.println("Has comido una bomba "+activo);
            System.out.println(marcador.getPuntuacion());

        }

    }
    private void prepareActionHandlers(String dir) {

        mainScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        switch (code){
                            case "RIGHT":
                                if (direccion.equals("LEFT")){}
                                else{
                                      direccion=code;
                                }
                                break;
                            case "LEFT":
                                if (direccion.equals("RIGHT")){}
                                else{
                                    direccion=code;
                                }
                                break;
                            case "DOWN":
                                if (direccion.equals("UP")){}
                                 else {
                                    direccion = code;
                                 }
                                break;
                            case "UP":
                                if (direccion.equals("DOWN")){}
                                else{
                                    direccion=code;
                                }
                                break;
                        }


                    }
                });
    }




    public static void main(String[] args) {
        launch(args);
    }
}
