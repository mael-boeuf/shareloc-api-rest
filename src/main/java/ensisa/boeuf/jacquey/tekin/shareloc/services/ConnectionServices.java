package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.UserDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
import ensisa.boeuf.jacquey.tekin.shareloc.security.TokenBuilder;
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
public class ConnectionServices {

    public ConnectionServices() {
    }

    @POST
    @Path("signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create an user and signup",
            tags = {"connection"},
            description = "Gets informations to create a new user. Miss informations will simulate API error conditions",
            responses = {
                    @ApiResponse(description = "An user", content = @Content(
                            schema = @Schema(implementation = User.class)
                    ))}
    )
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
                    description = "Firstname of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param firstname of user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("firstname") String firstname,
            @Parameter(
                    description = "Lastname of user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param lastname of user that needs to be fetched"
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
    @Operation(summary = "Signin with identifiants",
            tags = {"connection"},
            description = "Gets connection informations of user. Miss informations will simulate API error conditions"
    )
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
    @Operation(summary = "Get account properties",
            tags = {"connection"},
            description = "Gets account properties. Miss informations will simulate API error conditions")
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
