package edu.upc.dsa;

import edu.upc.dsa.models.Coordenadas;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.Persona;

import java.util.List;

public interface Manager {


    List<Persona> addUsuario(String nombre, String Apellidos, String email, String fechanacimiento, int id);
    List<Persona> OrdenarLista();
    Persona ConsultarInfo(int idPersona);
    void añadirPuntoInteres(int posicionx, int posiciony, ElementType tipo);
    void RegistroUsuario(int idUsuario, int posicionX, int posicionY);
    List<Coordenadas> CoordenadasUsuario(int idUsuario);
    List<Persona> listarUsuariosPorPuntoDeInteres(int x, int y);
    List<Coordenadas> consultarPuntosPorTipo(ElementType tipo);
    Coordenadas Buscacoordenadas(int x, int y );
    public int size();

    void clear();
}
