package sample;

public class Marcador {

    int puntuacion;
    int nivel;

    public Marcador(){
        puntuacion=0;
        nivel=0;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void suma(){
        puntuacion++;
    }

    public void resta(){
        puntuacion--;
    }
}
