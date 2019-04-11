package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Sprites.Bomba;
import sample.Sprites.Fondo;
import sample.Sprites.Fruta;
import sample.Sprites.Snake;

import java.io.File;
import java.util.Random;

public class Main extends Application {

    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static int alto = 600;
    static int ancho = 600;

    String puntos = "Puntuación: ";
    String nivelAct = "Nivel: ";
    String direccion ="LEFT";
    Random random= new Random();
    boolean frut = true, bomb= false;
    int nivel=5;

    Snake head = new Snake();
    Fruta fruta = new Fruta("fruta");
    Bomba bomba = new Bomba("bomba");
    Fondo fondo = new Fondo();
    Marcador marcador= new Marcador();

    Media media;
    MediaPlayer player;


    @Override
    public void start(Stage stage) {
        stage.setTitle("SNAKE");

        Group root = new Group();
        mainScene = new Scene(root);
        stage.setScene(mainScene);

        Canvas canvas = new Canvas(alto,ancho);
        root.getChildren().add(canvas);

        //String s =getClass().getClassLoader().getResource("sample\\Sounds\\music.mp3").toString();
        //System.out.println(s);
        String path = "file:///sample/music2.mp3";
        //.toPath().toString() C:/Users/EMILI/IdeaProjects/Snake/src/
        System.out.println(path);
        media = new Media(path);
        player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        fondo.setImage();
        fondo.setPosition(0,0);

        head.setImage("sample\\Images\\gatilen.png");
        head.setVelocity(1);
        head.setPosition((alto/2),(ancho/2));

        prepareActionHandlers(direccion);


        graphicsContext= canvas.getGraphicsContext2D();

        Font font = Font.font( "Helvetica", 15 );
        graphicsContext.setFont( font );
        graphicsContext.setStroke( Color.WHITE );
        graphicsContext.setLineWidth(1);

        fondo.render(graphicsContext);
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

                    if (!frut) {
                        pintarFruta();
                    }
                    fondo.clear(graphicsContext);
                    fondo.render(graphicsContext);
                    head.clear(graphicsContext);
                    head.update(direccion);
                    perder();
                    head.render(graphicsContext);
                    comprobarFruta(head);
                    renderitem();
                    lastUpdate = now;
                    if (marcador.getPuntuacion() == nivel){
                        head.setVelocity(head.getVelocity()+0.25);
                        System.out.println("velocidad aumentada");
                        nivel+=5;
                        marcador.setNivel(marcador.getNivel()+1);
                    }
                    String puntos = "Puntuación: "+marcador.getPuntuacion();
                    String nivelAct = "Nivel: "+marcador.getNivel();

                    graphicsContext.fillText(puntos,10,10);
                    graphicsContext.strokeText(puntos,10,20);

                    graphicsContext.fillText(nivelAct,530,10);
                    graphicsContext.strokeText(nivelAct,530,20);

                }
            }
        }.start();
        stage.show();
    }

    private void perder() {
        if (head.getPositionX() <= 0 || (head.getPositionX() + head.getWidth()) > ancho || head.getPositionY() <= 40 || (head.getPositionY() + head.getHeight()) > alto - 10) {
            System.out.println("Has perdido");
            System.exit(0);
            player.stop();
        }
    }
    private void pintarFruta(){
            fruta.setPosition(random.nextInt( (int) ( ancho - fruta.getWidth() ) ), random.nextInt( (int) ( alto - fruta.getHeight() ) ) );
            fruta.render(graphicsContext);
            frut=true;

    }
    private void comprobarFruta(Snake head){
        if (frut && fruta.intersects(head)) {
            fruta.clear(graphicsContext);
            fondo.clear(graphicsContext);
            fondo.render(graphicsContext);
            marcador.suma();
            frut=false;
            System.out.println(marcador.getPuntuacion());
        }

    }
    private void renderitem(){
        if (bomb){
            bomba.clear(graphicsContext);
            bomba.render(graphicsContext);
        }else if (frut){
            fruta.clear(graphicsContext);
            fruta.render(graphicsContext);
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
    private void pintarbomba() {
        bomba.setPosition(random.nextInt( (int) ( ancho - bomba.getWidth() ) ), random.nextInt( (int) ( alto - bomba.getHeight() ) ) );
        bomba.render(graphicsContext);
        bomb=true;
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
