package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.ColocationDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Colocation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("colocation")
public class ColocationServices extends AbstractServices<Colocation> {

    public ColocationServices() {
        super(Colocation.class);
    }

    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a colocation",
            tags = {"colocation"},
            description = "Gets informations to create a new colocation. Miss informations will simulate API error conditions",
            responses = {
                    @ApiResponse(description = "A colocation", content = @Content(
                            schema = @Schema(implementation = Colocation.class)
                    ))
            })
    public Response addColocation(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String name,
            @Parameter(
                    description = "Email of manager that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of manager that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("manager") String manager_email) {
        if (ColocationDao.createColocation(name, manager_email)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Remove a colocation",
            tags = {"colocation"},
            description = "Gets name of colocation and manager email to remove it. Miss informations will simulate API error conditions")
    public Response removeColocation(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String name,
            @Parameter(
                    description = "Email of manager that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of manager that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("manager") String manager_email) {
        if (ColocationDao.removeColocation(name, manager_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("invite")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Send an invitation",
            tags = {"colocation"},
            description = "Gets emails of invited user in a colocation. Miss informations will simulate API error conditions")
    public Response sendInvitation(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String name,
            @Parameter(
                    description = "Email of manager that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of manager that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("manager") String manager,
            @Parameter(
                    description = "Email of invited user that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of invited user that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("email") String email) {
        if (ColocationDao.inviteUserIntoColocation(name, manager, email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("editName")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a colocation name",
            tags = {"colocation"},
            description = "Gets new name of colocation. Miss informations will simulate API error conditions")
    public Response editColocationName(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String name,
            @Parameter(
                    description = "Email of manager that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of manager that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("manager") String manager_email,
            @Parameter(
                    description = "New name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param new name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("newName") String newName) {
        if (ColocationDao.editColocationName(name, manager_email, newName)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("removeMember")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Remove a colocation's member",
            tags = {"colocation"},
            description = "Gets emails of user to remove in colocation. Miss informations will simulate API error conditions")
    public Response removeMember(
            @Parameter(
                    description = "Name of colocation that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param name of colocation that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("name") String name,
            @Parameter(
                    description = "Email of manager that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of manager that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("manager") String manager_email,
            @Parameter(
                    description = "Email of user to remove that needs to be fetched",
                    schema = @Schema(
                            type = "string",
                            format = "string",
                            description = "Query param email of user to remove that needs to be fetched"
                    ),
                    required = true)
            @QueryParam("email") String member_email) {
        if (ColocationDao.removeMemberFromColoc(name, manager_email, member_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @GET
    @Path("best")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get best user",
            tags = {"colocation"},
            description = "Returns best user with higher points in colocation. Miss informations will simulation API error conditions")
    public Response getBestUser(
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
            @QueryParam("name") String name) {
        String msg = ColocationDao.getBestUser(email, name);
        if (msg != null) {
            return Response.ok(msg).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}