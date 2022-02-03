package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.UserDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
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

@Path("user")
@Api(value = "/user", consumes="application/json, application/xml")
public class UserServices extends AbstractServices<User> {

    public UserServices() {
        super(User.class);
    }

    @POST
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Edit profile properties",
            tags = {"user"},
            notes = "Sends new first name or/and last name of user. ",
            response = User.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "User's password", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "firstname", value = "User's new first name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "lastname", value = "User's new last name", required = false, dataType = "string", paramType = "query")
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
                    description = "First name of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param first name of user that needs to be fetched"
                    ),
                    required = false)
            @QueryParam("firstname") String firstname,
            @Parameter(
                    description = "Last name of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param last name of user that needs to be fetched"
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
    @ApiOperation(value = "Quit a colocation",
            tags = {"user"},
            notes = "Sends name of colocation to quit. Miss informations will simulate API error conditions",
            response = User.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "Colocation's name", required = true, dataType = "string", paramType = "query")
    })
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
    @ApiOperation(value = "Vote for a service",
            tags = {"user"},
            notes = "Sends number of vote for a service with his ID. Miss informations will simulate error conditions",
            response = User.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "serviceID", value = "ID's service", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "vote", value = "Number of votes for service", required = true, dataType = "int", paramType = "query")
    })
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
    @ApiOperation(value = "Validate an achieved service",
            tags = {"user"},
            notes = "Sends validation for an achieved service declaration. Miss informations will simulate API error conditions",
            response = User.class

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "achievedServiceID", value = "ID's achieved service", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "valid", value = "Achieved service's validation", required = true, dataType = "boolean", paramType = "query")
    })
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