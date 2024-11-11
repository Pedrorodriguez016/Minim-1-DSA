package edu.upc.dsa.models;

public class Coordenadas {
    private int PosicionX;
    private int PosicionY;
    private ElementType puntointeres;

    public Coordenadas(){}

    public void setPosicionX(int posicionX) {
        PosicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        PosicionY = posicionY;
    }

    public void setPuntointeres(ElementType puntointeres) {
        this.puntointeres = puntointeres;
    }

    public ElementType getPuntointeres() {
        return puntointeres;
    }

    public int getPosicionX() {
        return PosicionX;
    }

    public int getPosicionY() {
        return PosicionY;
    }

    public Coordenadas(ElementType puntointeres, int posicionX, int posicionY){
        this.puntointeres=puntointeres;
        this.PosicionX=posicionX;
        this.PosicionY= posicionY;
    }
}
