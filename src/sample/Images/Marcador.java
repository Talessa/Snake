package sample.Images;

public class Marcador {

    int puntuacion;

    public Marcador(){
        puntuacion=0;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void suma(){
        puntuacion++;
    }

    public void resta(){
        puntuacion--;
    }
}
