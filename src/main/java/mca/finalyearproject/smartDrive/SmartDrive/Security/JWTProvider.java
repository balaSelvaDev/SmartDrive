package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTProvider {

    public String genereteJwtToken(UserPrincipal principal) {
        String sign = JWT.create().withIssuer(SecurityConstant.ISSUER).withSubject(principal.getUsername())
                .withKeyId(String.valueOf(principal.getUserId())).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (SecurityConstant.TOKEN_EXPIRE_TIME)))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY));
        return sign;
    }

    public String getSubjectFromToken(String extractToken) {

        JWTVerifier jwtVerifierMethod = JwtVerifierMethod();
        return jwtVerifierMethod.verify(extractToken).getSubject();

    }

    private JWTVerifier JwtVerifierMethod() {
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstant.SECRET_KEY);
        return JWT.require(algorithm).withIssuer(SecurityConstant.ISSUER).build();
    }

    public boolean isValidToken(String extractToken) {
        JWTVerifier jwtVerifierMethod = JwtVerifierMethod();
        Date issuedAt = jwtVerifierMethod.verify(extractToken).getExpiresAt();
        return !issuedAt.before(new Date());
    }

}
