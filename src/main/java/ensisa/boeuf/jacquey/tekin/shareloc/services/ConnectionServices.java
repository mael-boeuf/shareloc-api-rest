package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.UserDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
import ensisa.boeuf.jacquey.tekin.shareloc.security.TokenBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ConnectionServices {

    public ConnectionServices() {
    }

    @POST
    @Path("signup")
    public Response signup(@QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        if (UserDao.createUser(email, password, firstname, lastname))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.CONFLICT).build();

    }

    @POST
    @Path("signin")
    public Response signin(@QueryParam("email") String email, @QueryParam("password") String password){
        User user = UserDao.login(email,password);
        if(user != null) {
            return Response.ok().entity(TokenBuilder.buildToken(user.getEmail())).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Path("whoami")
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
