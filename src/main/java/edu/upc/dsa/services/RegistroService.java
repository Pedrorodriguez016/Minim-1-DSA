package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Coordenadas;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.Persona;
import edu.upc.dsa.models.Registro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/registro", description = "Endpoint to Persona Service")
@Path("/registro")
public class RegistroService {

    private Manager tm;

    public RegistroService() {
        this.tm = ManagerImpl.getInstance();
        this.tm.addUsuario("Juan", "Perez", "juan.perez@mail.com", "2000-01-01", 1);
        this.tm.addUsuario("Ana", "Gomez", "ana.gomez@mail.com", "2001-02-02", 2);
        this.tm.añadirPuntoInteres(1, 1, ElementType.valueOf("DOOR"));
        this.tm.añadirPuntoInteres(2, 2, ElementType.valueOf("BRIDGE"));
    }
    @POST
    @ApiOperation(value = "crea un nuevo registro de una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Registro.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{id}/{x}/{y}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newRegistro(@PathParam("id") int id, @PathParam("x") int x, @PathParam("y") int y) {

        try {
            this.tm.RegistroUsuario(id, x, y);
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(404).entity("User or Point not found").build();
        }
    }
    @GET
    @ApiOperation(value = "Encuentra usuarios por punto de interes", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Coordenadas.class),
            @ApiResponse(code = 404, message = "Coordenadas not found")
    })
    @Path("Personas/{x}/{y}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosInteresPorUser(@PathParam("x") int x, @PathParam("y") int y) {
        List<Persona> p = this.tm.listarUsuariosPorPuntoDeInteres(x, y);
        if (p == null|| p.isEmpty()) {
            return Response.status(404).build();
        }
        GenericEntity<List<Persona>> entity = new GenericEntity<List<Persona>>(p) {};
        return Response.status(201).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "Encuentra coordenadas por punto de interes", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Coordenadas.class),
            @ApiResponse(code = 404, message = "Coordenadas not found")
    })
    @Path("Coordenadas/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoordenadasPorUser(@PathParam("UserID") int id) {
        List<Coordenadas> c = this.tm.CoordenadasUsuario(id);
        if (c == null|| c.isEmpty()) {
            return Response.status(404).build();
        }
        GenericEntity<List<Coordenadas>> entity = new GenericEntity<List<Coordenadas>>(c) {};
        return Response.status(201).entity(entity).build();
    }

}
