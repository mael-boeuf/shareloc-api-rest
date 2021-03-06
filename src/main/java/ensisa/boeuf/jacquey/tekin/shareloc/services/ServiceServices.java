package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.ServiceDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("service")
@Api(value = "/service", consumes="application/json, application/xml")
public class ServiceServices extends AbstractServices<Service>{

    public ServiceServices() {
        super(Service.class);
    }

    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a new service",
            tags = {"service"},
            notes = "Sends informations to create a new service. Miss informations will simulate API error conditions",
            response = Service.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Colocation's name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "Service's title", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "Service's description", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cost", value = "Beneficiary users", required = true, dataType = "int", paramType = "query")
    })
    public Response newService(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String colocationName,
            @Parameter(
                    description = "Title of service that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param title of service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("title") String title,
            @Parameter(
                    description = "Description of service that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param description of service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("description") String description,
            @Parameter(
                    description = "Cost of service that needs to be fetched",
                    schema = @Schema(
                            type = "integer",
                            format = "int64",
                            description = "Query param cost of service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("cost") int cost) {
        if(ServiceDao.createService(title, description, cost, colocationName)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


}