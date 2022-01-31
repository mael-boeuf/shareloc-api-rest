package ensisa.boeuf.jacquey.tekin.shareloc.security;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenBuilder {

    public static String buildToken(String subject) {
        RsaJsonWebKey rsaJsonWebKey = produceRsaKey();

        JwtClaims claims = new JwtClaims();
        claims.setSubject(subject);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            Logger.getLogger(AuthFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jwt;
    }

    public static RsaJsonWebKey produceRsaKey() {
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
