package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.AbstractDao;
import ensisa.boeuf.jacquey.tekin.shareloc.controllers.Dao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class AbstractServices<T> {

    private AbstractDao<T> dao = null;

    public AbstractServices(Class<T> serviceClass) {
        this.dao = new AbstractDao<T>(serviceClass);
    }

    public AbstractServices() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(dao.findAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find an object in dao by ID",
            tags = {"services"},
            notes = "Returns an object in dao. Null object will simulate API error conditions",
            response = Dao.class
    )
    public Response get(
            @Parameter(
                    description = "ID of dao that needs to be fetched",
                    schema = @Schema(
                            type = "integer",
                            format = "int64",
                            description = "Param ID of dao that needs to be fetched"
                    ),
                    required = true)
            @ApiParam(value = "Dao's id", required = true)
            @PathParam("id") Integer id) {
        final T obj = dao.find(id);
        if (obj == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(obj).build();
    }
}
