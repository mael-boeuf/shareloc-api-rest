package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.UserDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
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

@Path("user")
public class UserServices extends AbstractServices<User> {

    public UserServices() {
        super(User.class);
    }

    @POST
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit profile properties",
            tags = {"user"},
            description = "Gets new firstname or/and lastname of user. ",
            responses = {
                    @ApiResponse(description = "An user", content = @Content(
                            schema = @Schema(implementation = User.class)
                    ))
            })
    public Response editFirstLastNames(
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
                    description = "Password of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param password of user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("password") String password,
            @Parameter(
                    description = "Firstname of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param firstname of user that needs to be fetched"
                    ),
                    required = false)
            @QueryParam("firstname") String firstname,
            @Parameter(
                    description = "Lastname of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param lastname of user that needs to be fetched"
                    ),
                    required = false)
            @QueryParam("lastname") String lastname) {
        if (UserDao.editUser(email, password, firstname, lastname)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("quit")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Quit a colocation",
            tags = {"user"},
            description = "Gets name of colocation to quit. Miss informations will simulate API error conditions")
    public Response quitColocation(
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
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String coloc_name) {
        if (UserDao.quitColocation(email, coloc_name)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("vote")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Vote for a service",
            tags = {"user"},
            description = "Gets number of vote for a service with his ID. Miss informations will simulate error conditions")
    public Response vote(
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
                    description = "Number of vote that needs to be fetched",
                    schema = @Schema(
                            type = "integer",
                            format = "int64",
                            description = "Query param number of vote that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("vote") int vote) {
        if (UserDao.vote(email, serviceID, vote)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("valid")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Validate an achieved service",
            tags = {"user"},
            description = "Gets validation for an achieved service declaration. Miss informations will simulate API error conditions")
    public Response valid(
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
                    description = "ID of achieved service that needs to be fetched",
                    schema = @Schema(
                            type = "integer",
                            format = "long",
                            description = "Query param ID of achieved service that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("achievedServiceID") Long achievedServiceID,
            @Parameter(
                    description = "Validation of achieved service declaration that needs to be fetched",
                    schema = @Schema(
                            type = "boolean",
                            format = "boolean",
                            description = "Query param validation of achieved service declaration that needs to be fetched",
                            allowableValues = {"true","false"}
                    ),
                    required = true)
            @QueryParam("valid") boolean validated) {
        if (UserDao.valid(email, achievedServiceID, validated)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}