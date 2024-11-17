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

@Api(value = "/coordenada", description = "Endpoint to coordenada Service")
@Path("/coordenada")
public class CoordenadasService {

    private Manager tm;

    public CoordenadasService() {
        this.tm = ManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.añadirPuntoInteres(1, 1, ElementType.valueOf("DOOR"));
            this.tm.añadirPuntoInteres(2, 2, ElementType.valueOf("BRIDGE"));
            this.tm.addUsuario("Juan", "Perez", "juan.perez@mail.com", "2000-01-01", 1);
            this.tm.addUsuario("Ana", "Gomez", "ana.gomez@mail.com", "2001-02-02", 2);
        }


    }

    @POST
    @ApiOperation(value = "crear nuevas Coordenadas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Coordenadas.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newCoordenada(Coordenadas coordenada) {

        if (coordenada.getPuntointeres()==null)  return Response.status(500).entity(coordenada).build();
        this.tm.añadirPuntoInteres(coordenada.getPosicionX(), coordenada.getPosicionY(),coordenada.getPuntointeres());
        return Response.status(201).entity(coordenada).build();
    }



    @GET
    @ApiOperation(value = "Encuentra coordenadas por punto de interes", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Coordenadas.class),
            @ApiResponse(code = 404, message = "Coordenadas not found")
    })
    @Path("tipo/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoordenadas(@PathParam("tipo") ElementType tipo) {
        List<Coordenadas> c = this.tm.consultarPuntosPorTipo(tipo);
        if (c == null|| c.isEmpty()) {
            return Response.status(404).build();
        }
        GenericEntity<List<Coordenadas>> entity = new GenericEntity<List<Coordenadas>>(c) {};
        return Response.status(201).entity(entity).build();
    }



}
