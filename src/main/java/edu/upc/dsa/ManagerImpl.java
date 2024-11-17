package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;
import edu.upc.dsa.models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {
    private static Manager instance;
    protected List<Persona> usuarios;
    protected List<Coordenadas> coordenadas;
    protected List<Registro> registros;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    private ManagerImpl() {

        this.usuarios = new LinkedList<>();
        this.coordenadas= new ArrayList<>();
        this.registros= new ArrayList<>();
    }

    public static Manager getInstance() {
        if (instance==null) instance = new ManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.usuarios.size();
        logger.info("size " + ret);

        return ret;
    }
    @Override
    public List<Persona> addUsuario(String nombre, String Apellidos, String email, String fechanacimiento, int id){
        logger.info("Añadiendo el usuario con nombre " + nombre);
        Persona usuario = new Persona(nombre,Apellidos,id,email,fechanacimiento);
        usuarios.add(usuario);
        return usuarios;
    }
    @Override
    public List<Persona> OrdenarLista(){
        logger.info("Ordenando la lista alfabeticamente");
        usuarios.sort(Comparator.comparing(Persona::getApellido).thenComparing(Persona::getNombre));
        return usuarios;
    }
    @Override
    public Persona ConsultarInfo(int idPersona){
        logger.info("Consultando informacion de usuario con id " + idPersona);
        for(Persona persona : usuarios){
            if(persona.getId()== idPersona){
                return persona;
            }
        }
        logger.error("el usuario no existe");
        return null;
    }
    @Override
    public void añadirPuntoInteres(int posicionx, int posiciony, ElementType tipo){
        logger.info("Añadiendo punto " + posicionx+ ", "+ posiciony);
        Coordenadas coordenada= new Coordenadas(tipo, posicionx,posiciony);
        coordenadas.add(coordenada);

    }
    @Override
    public void RegistroUsuario(int idUsuario, int posicionX, int posicionY){
        logger.info("Registrando punto al usuario "+ idUsuario);
        Persona persona= ConsultarInfo(idUsuario);
        Coordenadas coordenada = Buscacoordenadas(posicionX,posicionY);
        if(persona!=null){
            Registro registro= new Registro(persona,coordenada);
            registros.add(registro);
            logger.info("Punto añadido");
        }
        else{
            logger.error("usuario o coordenadas inexistentes");
        }
    }
    @Override
    public List<Coordenadas> CoordenadasUsuario(int idUsuario){
        List<Coordenadas> registrocoordenadas = new ArrayList<>();
        for(Registro registro: registros){
            if(registro.getPersona().getId()== idUsuario){
                registrocoordenadas.add(registro.getCoordenadas());
            }
        }
        return registrocoordenadas;
    }
    @Override
    public List<Persona> listarUsuariosPorPuntoDeInteres(int x, int y) {
        logger.info("Listando usuarios que han pasado por el punto de interés (" + x + ", " + y + ")");
        Coordenadas punto = Buscacoordenadas(x, y);
        if (punto == null) {
            logger.error("Error: Punto de interés no encontrado");
            return new ArrayList<>();
        }

        List<Persona> usuariosPorPunto = new ArrayList<>();
        for (Registro registro : registros) {
            if (registro.getCoordenadas().equals(punto)) {
                usuariosPorPunto.add(registro.getPersona());
            }
        }
        return usuariosPorPunto;
    }
    @Override
    public List<Coordenadas> consultarPuntosPorTipo(ElementType tipo) {
        logger.info("Consultando puntos de interés de tipo: " + tipo);
        List<Coordenadas> puntos = new ArrayList<>();
        for (Coordenadas punto : coordenadas) {
            if (punto.getPuntointeres() == tipo) {
                puntos.add(punto);
            }
        }
        return puntos;
    }
    @Override
    public Coordenadas Buscacoordenadas(int x, int y ){
        for(Coordenadas coordenada : coordenadas) {
            if (coordenada.getPosicionX() == x && coordenada.getPosicionY() == y)
                return coordenada;

        }
        return null;

    }

    public void clear() {
        this.usuarios.clear();
        this.coordenadas.clear();
        this.registros.clear();
    }


}