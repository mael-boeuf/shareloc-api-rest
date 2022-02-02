package ensisa.boeuf.jacquey.tekin.shareloc.services;

import ensisa.boeuf.jacquey.tekin.shareloc.controllers.ServiceDao;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("service")
public class ServiceServices extends AbstractServices<Service>{

    public ServiceServices() {
        super(Service.class);
    }

    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newService(@QueryParam("name") String colocationName, @QueryParam("title") String title, @QueryParam("description") String description, @QueryParam("cost") int cost) {
        if(ServiceDao.createService(title, description, cost, colocationName)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


}
