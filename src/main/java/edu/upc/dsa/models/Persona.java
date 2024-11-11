package edu.upc.dsa.models;

public class Persona {
    private String nombre;
    private String apellido;
    private int id;
    private String email;
    private String fechaNacimiento;
    public Persona(){}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Persona(String nombre, String apellido, int id, String Email, String fechaNacimiento){
        this.nombre= nombre;
        this.apellido=apellido;
        this.id= id;
        this.email=Email;
        this.fechaNacimiento=fechaNacimiento;
    }
}
