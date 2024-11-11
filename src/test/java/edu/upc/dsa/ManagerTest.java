package edu.upc.dsa;

import edu.upc.dsa.models.Coordenadas;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.Persona;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ManagerTest {
    Manager tm;

    @Before
    public void setUp() {
        this.tm = ManagerImpl.getInstance();
        this.tm.addUsuario("Juan", "Perez", "juan.perez@mail.com", "2000-01-01", 1);
        this.tm.addUsuario("Ana", "Gomez", "ana.gomez@mail.com", "2001-02-02", 2);
        this.tm.añadirPuntoInteres(1, 1, ElementType.valueOf("DOOR"));
        this.tm.añadirPuntoInteres(2, 2, ElementType.valueOf("BRIDGE"));
    }

    @After
    public void tearDown() {
        // És un Singleton
        this.tm.clear();
    }

    @Test
    public void addUsuarioTest() {

        List<Persona> usuario = tm.addUsuario("Pedro", "Rodriguez", "pedro.rodriguez@gmail.com", "2003-07-02", 3);
        Assert.assertEquals(3, tm.size());

    }

    @Test
    public void getUsuariosTest() throws Exception {
        Assert.assertEquals(2, tm.size());
        tm.OrdenarLista();
        List<Persona> usuariosOrdenados = tm.OrdenarLista();
        Assert.assertEquals("Gomez", usuariosOrdenados.get(0).getApellido()); // Verificar el orden
        Assert.assertEquals("Perez", usuariosOrdenados.get(1).getApellido());

    }

    @Test
    public void getUsuarioInfoTest() {
        Persona usuario = tm.ConsultarInfo(1);
        Assert.assertNotNull(usuario);
        Assert.assertEquals("2000-01-01", usuario.getFechaNacimiento());


    }

    @Test
    public void AñadirPuntoInteresTest() {
        tm.añadirPuntoInteres(3, 3, ElementType.COIN);
        Coordenadas coordenada = tm.Buscacoordenadas(3, 3);
        Assert.assertNotNull(coordenada);
        Assert.assertEquals(ElementType.COIN, coordenada.getPuntointeres());
    }


    @Test
    public void RegistroUsuarioTest() {
        tm.RegistroUsuario(1, 1, 1);
        List<Coordenadas> puntosVisitados = tm.CoordenadasUsuario(1);
        Assert.assertEquals(1, puntosVisitados.size());
        Assert.assertEquals(1, puntosVisitados.get(0).getPosicionX());
        Assert.assertEquals(1, puntosVisitados.get(0).getPosicionY());

    }

    @Test
    public void testCoordenadasUsuario() {
        tm.RegistroUsuario(1, 1, 1);
        tm.RegistroUsuario(1, 2, 2);
        List<Coordenadas> puntosVisitados = tm.CoordenadasUsuario(1);
        Assert.assertEquals(2, puntosVisitados.size());
    }

    @Test
    public void testListarUsuariosPorPuntoDeInteres() {
        tm.RegistroUsuario(1, 1, 1);
        tm.RegistroUsuario(2, 1, 1);
        List<Persona> usuariosPorPunto = tm.listarUsuariosPorPuntoDeInteres(1, 1);
        Assert.assertEquals(2, usuariosPorPunto.size());
        Assert.assertEquals("Perez", usuariosPorPunto.get(0).getApellido());
        Assert.assertEquals("Gomez", usuariosPorPunto.get(1).getApellido());
    }
    @Test
    public void testConsultarPuntosPorTipo() {
        List<Coordenadas> coordenadasTipoBridge = tm.consultarPuntosPorTipo(ElementType.BRIDGE);
        Assert.assertEquals(1, coordenadasTipoBridge.size());
        Assert.assertEquals(2, coordenadasTipoBridge.get(0).getPosicionX());
        Assert.assertEquals(2, coordenadasTipoBridge.get(0).getPosicionY());
    }
}
