package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.Persona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/persona", description = "Endpoint to Persona Service")
@Path("/persona")
public class PersonaService {

    private Manager tm;

    public PersonaService() {
        this.tm = ManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addUsuario("Juan", "Perez", "juan.perez@mail.com", "2000-01-01", 1);
            this.tm.addUsuario("Ana", "Gomez", "ana.gomez@mail.com", "2001-02-02", 2);

        }


    }

    @GET
    @ApiOperation(value = "Lista todos los usuarios", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Persona.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonas() {

        List<Persona> usuarios = this.tm.OrdenarLista();

        GenericEntity<List<Persona>> entity = new GenericEntity<List<Persona>>(usuarios) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Encuentra una persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Persona.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersona(@PathParam("id") int id) {
        Persona p = this.tm.ConsultarInfo(id);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }

    @POST
    @ApiOperation(value = "añade una Persona", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Persona.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPersona(Persona usuario) {

        if (usuario.getNombre()==null || usuario.getApellido()==null || usuario.getFechaNacimiento()== null || usuario.getEmail() == null)  return Response.status(500).entity(usuario).build();
        this.tm.addUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getId());
        return Response.status(201).entity(usuario).build();
    }

}
