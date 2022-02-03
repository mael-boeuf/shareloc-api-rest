package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.UserDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
import ensisa.boeuf.jacquey.tekin.shareloc.security.TokenBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/")
@Api(value = "/", consumes="application/json, application/xml")
public class ConnectionServices {

    public ConnectionServices() {
    }

    @POST
    @Path("signup")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create an user and signup",
            tags = {"connection"},
            notes = "Sends informations to create a new user. Miss informations will simulate API error conditions",
            response = User.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "User's password", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "firstname", value = "User's first name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "lastname", value = "User's last name", required = true, dataType = "string", paramType = "query")
    })
    public Response signup(
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
                    required = true)
            @QueryParam("firstname") String firstname,
            @Parameter(
                    description = "Last name of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param last name of user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("lastname") String lastname) {
        if (UserDao.createUser(email, password, firstname, lastname))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.CONFLICT).build();

    }

    @POST
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Signin with identifiants",
            tags = {"connection"},
            notes = "Sends connection informations of user. Miss informations will simulate API error conditions"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "User's password", required = true, dataType = "string", paramType = "query")
    })
    public Response signin(
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
                            description = "Query param Password of user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("password") String password){
        User user = UserDao.login(email,password);
        if(user != null) {
            return Response.ok().entity(TokenBuilder.buildToken(user.getEmail())).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Path("myprofile")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get account properties",
            tags = {"connection"},
            notes = "Gets account properties. Miss informations will simulate API error conditions")
    public Response whoami(@Context SecurityContext security) {
        User user = UserDao.getUser(security.getUserPrincipal().getName());
        if (user!=null)
            return Response.ok().entity(user).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static List<String> findUserRoles(String user) {
        return null;
    }

}
