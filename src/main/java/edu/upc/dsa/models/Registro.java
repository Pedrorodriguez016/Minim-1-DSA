package edu.upc.dsa.models;

public class Registro {

    private Persona persona;
    private Coordenadas coordenadas;

    public Registro(){}

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public Persona getPersona() {
        return persona;
    }

    public Registro(Persona persona, Coordenadas coordenadas){

        this.persona= persona;
        this.coordenadas=coordenadas;
    }
}
