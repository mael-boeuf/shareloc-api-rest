package ensisa.boeuf.jacquey.tekin.shareloc.security;

import ensisa.boeuf.jacquey.tekin.shareloc.services.ConnectionServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeaderVal = requestContext.getHeaderString("Authorization");

        if (authHeaderVal.startsWith("Bearer")) {
            try {
                final String subject = validate(authHeaderVal.split(" ")[1]);
                final SecurityContext securityContext = requestContext.getSecurityContext();
                if (subject != null) {
                    System.out.println("Subject not null ! ");
                    requestContext.setSecurityContext(new SecurityContext() {
                        @Override
                        public Principal getUserPrincipal() {
                            return new Principal() {
                                @Override
                                public String getName() {
                                    return subject;
                                }
                            };
                        }

                        @Override
                        public boolean isUserInRole(String role) {
                            List<String> roles = ConnectionServices.findUserRoles(subject);
                            if (roles != null)
                                return roles.contains(role);
                            return false;
                        }

                        @Override
                        public boolean isSecure() {
                            return securityContext.isSecure();
                        }

                        @Override
                        public String getAuthenticationScheme() {
                            return securityContext.getAuthenticationScheme();
                        }
                    });
                }
            } catch (InvalidJwtException ex) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private String validate (String jwt) throws InvalidJwtException {
        String subject = null;
        RsaJsonWebKey rsaJsonWebKey = produceRsaKey();
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject().setVerificationKey(rsaJsonWebKey.getKey()).build();
        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        subject = (String) jwtClaims.getClaimValue("sub");

        return subject;
    }

    public RsaJsonWebKey produceRsaKey() {
        RsaJsonWebKey theOne = null;
        if (theOne == null) {
            try {
                theOne = RsaJwkGenerator.generateJwk(2048);
            } catch (JoseException ex) {
                ex.printStackTrace();
            }
        }
        return theOne;
    }

}