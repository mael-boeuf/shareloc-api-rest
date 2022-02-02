package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.AchievedServiceDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.AchievedService;
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
import java.util.List;

@Path("achievedService")
public class AchievedServiceServices extends AbstractServices<AchievedService> {

    public AchievedServiceServices() {
        super(AchievedService.class);
    }

    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create new achieved service",
            tags = {"achievedService"},
            description = "Gets informations to create a new achieved service. Miss informations will simulate API error conditions",
            responses = {
                    @ApiResponse(description = "An Achieved Service", content = @Content(
                            schema = @Schema(implementation = AchievedService.class)
                    ))
            })
    public Response newAchievedService(
            @Parameter(
                    description = "Email of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("email") String email,
            @Parameter(
                    description = "ID of service that needs to be fetched",
                    schema = @Schema(
                            type = "integer",
                            format = "long",
                            description = "Query param ID of service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("serviceID") Long serviceID,
            @Parameter(
                    description = "Creation date of achieved service that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param creation date of achieved service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("date") String date,
            @Parameter(
                    description = "Picture of achieved service that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param picture of achieved service that needs to be fetched"
                    ),
                    required = false)
            @QueryParam("picture") String picture,
            @Parameter(
                    description = "Beneficiary of achieved service that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param beneficiary of achieved service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("to") List<String> to) {
        if (AchievedServiceDao.newAchievedService(email, serviceID, date, picture,to)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}