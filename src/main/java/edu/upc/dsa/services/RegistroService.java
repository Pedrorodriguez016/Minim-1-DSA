package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
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
    }
    @POST
    @ApiOperation(value = "crea un nuevo registro de una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Persona.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Registro registro) {

        if ((registro.getPersona().getId() == 99) || (registro.getCoordenadas().getPosicionX() == 99) ||registro.getCoordenadas().getPosicionY() == 99)
            return Response.status(500).entity(registro).build(); // teniendo en cuenta que solo puedo tener 99 usuarios y que las coordenadas minimo llegan a 99 unidades
        this.tm.RegistroUsuario(registro.getPersona().getId(), registro.getCoordenadas().getPosicionX(), registro.getCoordenadas().getPosicionY());
        return Response.status(201).entity(registro).build();
    }

}
