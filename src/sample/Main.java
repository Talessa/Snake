package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Sprites.Bomba;
import sample.Sprites.Fondo;
import sample.Sprites.Fruta;
import sample.Sprites.Snake;

import java.util.Random;

public class Main extends Application {

    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static int alto = 300;
    static int ancho = 300;

    String direccion ="LEFT";
    Random random= new Random();
    boolean frut = true, bomb= false;
    int nivel=5;

    Snake head = new Snake();
    Fruta fruta = new Fruta("fruta");
    Bomba bomba = new Bomba("bomba");
    Fondo fondo = new Fondo();
    Marcador marcador= new Marcador();

    @Override
    public void start(Stage stage) {
        stage.setTitle("SNAKE");

        Group root = new Group();
        mainScene = new Scene(root);
        stage.setScene(mainScene);

        Canvas canvas = new Canvas(alto,ancho);
        root.getChildren().add(canvas);

        fondo.setImage();
        fondo.setPosition(0,0);

        head.setImage("sample\\Images\\gatilen.png");
        head.setVelocity(1);
        head.setPosition((alto/2),(ancho/2));

        prepareActionHandlers(direccion);


        graphicsContext= canvas.getGraphicsContext2D();

        fondo.render(graphicsContext);
        head.render(graphicsContext);

        fruta.setImage("sample\\Images\\fruta.png");
        bomba.setImage("sample\\Images\\bomba.png");


        fruta.setPosition(random.nextInt(ancho),random.nextInt(alto));
        fruta.render(graphicsContext);


        new AnimationTimer(){
            long lastUpdate = 0;
//            long bombaRender =0;
//            long bombaClear = 0;
//            long nowBombarender=0;
//            long nowBombaClear =0;
            @Override
            public void handle(long now) {
//                nowBombaClear=now;
//                bombaClear=now;
                if (now - lastUpdate >= 10_000_000) {

                    if (!frut) {
                        pintarFruta();
                    }
//                    if (!bomb && nowBombarender - bombaRender >= 60_000_000){
//                        pintarbomba();
//                        bombaRender = now;
//                    }
//                    if (bomb && nowBombaClear - bombaClear >=50_000_000){
//                        bomba.clear(graphicsContext);
//                        bombaClear = now;
//                        bomb=false;
//
//                    }
                    fondo.clear(graphicsContext);
                    fondo.render(graphicsContext);
                    head.clear(graphicsContext);
                    head.update(direccion);
                    if (head.getPositionX() < 0 || (head.getPositionX() + head.getWidth()) > ancho || head.getPositionY() < 0 || (head.getPositionY() + head.getHeight()) > alto - 10) {
                        System.out.println("Has perdido");
                        System.exit(0);
                    }
                    head.render(graphicsContext);
                    comprobarFruta(head);
                    comprobarBomba(head);
                    renderitem();

                    lastUpdate = now;

                    if (marcador.getPuntuacion() == nivel){
                        head.setVelocity(head.getVelocity()+0.25);
                        System.out.println("velocidad aumentada");
                        nivel+=5;
                    }
                }
            }
        }.start();
        stage.show();
    }



    private void pintarbomba() {
        bomba.setPosition(random.nextInt( (int) ( ancho - bomba.getWidth() ) ), random.nextInt( (int) ( alto - bomba.getHeight() ) ) );
        bomba.render(graphicsContext);
        bomb=true;
    }

    public void pintarFruta(){
            fruta.setPosition(random.nextInt( (int) ( ancho - fruta.getWidth() ) ), random.nextInt( (int) ( alto - fruta.getHeight() ) ) );
            fruta.render(graphicsContext);
            frut=true;

    }
    public void comprobarFruta(Snake head){
        if (frut && fruta.intersects(head)) {
            fruta.clear(graphicsContext);
            fondo.clear(graphicsContext);
            fondo.render(graphicsContext);
            marcador.suma();
            frut=false;
            System.out.println(marcador.getPuntuacion());
        }

    }
    private void comprobarBomba(Snake head) {
        if (bomb && bomba.intersects(head)) {
            bomba.clear(graphicsContext);
            fondo.clear(graphicsContext);
            fondo.render(graphicsContext);
            marcador.resta();
            bomb=false;
            System.out.println(marcador.getPuntuacion());
        }
    }
    public void renderitem(){
        if (bomb){
            bomba.clear(graphicsContext);
            bomba.render(graphicsContext);
        }else if (frut){
            fruta.clear(graphicsContext);
            fruta.render(graphicsContext);
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
