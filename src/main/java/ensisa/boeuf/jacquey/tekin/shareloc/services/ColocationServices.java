package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.ColocationDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Colocation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("colocation")
@Produces(MediaType.APPLICATION_JSON)
public class ColocationServices extends AbstractServices<Colocation> {

    public ColocationServices() {
        super(Colocation.class);
    }

    @POST
    @Path("new")
    public Response addColocation(@QueryParam("name") String name, @QueryParam("admin") String admin_email) {
        if (ColocationDao.createColocation(name, admin_email)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("remove")
    public Response removeColocation(@QueryParam("name") String name, @QueryParam("admin") String admin_email) {
        if (ColocationDao.removeColocation(name, admin_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("invite")
    public Response sendInvitation(@QueryParam("name") String name, @QueryParam("admin") String admin, @QueryParam("email") String email) {
        if (ColocationDao.inviteUserIntoColocation(name, admin, email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("editName")
    public Response editColocationName(@QueryParam("name") String name, @QueryParam("admin") String admin_email, @QueryParam("newName") String newName) {
        if (ColocationDao.editColocationName(name, admin_email, newName)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("removeMember")
    public Response removeMember(@QueryParam("name") String name, @QueryParam("admin") String admin_email, @QueryParam("email") String member_email) {
        if (ColocationDao.removeMemberFromColoc(name, admin_email, member_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @GET
    @Path("best")
    public Response getBestUser(@QueryParam("email") String email, @QueryParam("name") String name) {
        String msg = ColocationDao.getBestUser(email, name);
        if (msg != null) {
            return Response.ok(msg).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}