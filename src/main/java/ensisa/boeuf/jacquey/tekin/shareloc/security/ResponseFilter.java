package ensisa.boeuf.jacquey.tekin.shareloc.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (requestContext.getProperty("auth-failed") != null) {
            Boolean failed = (Boolean) requestContext.getProperty("auth-failed");
            if (failed) {
                return;
            }
        }

        List<Object> jwt = new ArrayList<Object>();
        jwt.add(requestContext.getHeaderString("Authorization").split(" ")[1]);
        responseContext.getHeaders().put("jwt", jwt);

    }
}