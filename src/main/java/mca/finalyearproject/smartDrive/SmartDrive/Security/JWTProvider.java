package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTProvider {

    //    public String genereteJwtToken(UserPrincipal principal) {
//        String sign = JWT.create().withIssuer(SecurityConstant.ISSUER).withSubject(principal.getUsername())
//                .withKeyId(String.valueOf(principal.getUserId())).withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + (SecurityConstant.TOKEN_EXPIRE_TIME)))
//                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY));
//        return sign;
//    }
//
    public String getSubjectFromToken1(String extractToken) {

        JWTVerifier jwtVerifierMethod = JwtVerifierMethod();
        return jwtVerifierMethod.verify(extractToken).getSubject();

        /// /        try {
        /// /            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET_KEY))
        /// /                    .build()
        /// /                    .verify(extractToken);
        /// /            return decodedJWT.getSubject();
        /// /        } catch (JWTDecodeException e) {
        /// ///            log.error("Invalid token structure: {}", e.getMessage());
        /// /            System.out.println("Invalid token structure: {}" + e.getMessage());
        /// /            return null;
        /// /        }
//
    }

    //
//    private JWTVerifier JwtVerifierMethod() {
//        Algorithm algorithm = Algorithm.HMAC512(SecurityConstant.SECRET_KEY);
//        return JWT.require(algorithm).withIssuer(SecurityConstant.ISSUER).build();
//    }
//

    public String generateJwtToken(UserPrincipal principal) {
        byte[] decodedKey = Base64.getDecoder().decode(SecurityConstant.SECRET_KEY);

        Algorithm algorithm = Algorithm.HMAC512(SecurityConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        String token = JWT.create()
                .withIssuer(SecurityConstant.ISSUER)                        // who issued the token
                .withSubject(principal.getUsername())                       // username as subject
                .withKeyId(String.valueOf(principal.getUserId()))           // userId as Key ID
                .withClaim("userId", principal.getUserId())                 // ✅ added userId
//                .withClaim("email", principal.getEmail())                   // ✅ added email
                .withClaim("roles", principal.getAuthorities()
                        .stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .collect(Collectors.toList()))                      // ✅ roles list
//                .withClaim("fullName", principal.getFullName())             // ✅ added full name
                .withIssuedAt(new Date())                                   // issued time
                .withExpiresAt(new Date(System.currentTimeMillis() +
                        SecurityConstant.TOKEN_EXPIRE_TIME)) // expiry
                .sign(algorithm);
        return token;
    }

    public String getSubjectFromToken(String extractToken) {
        try {
            if (extractToken == null || extractToken.trim().isEmpty()) {
                throw new IllegalArgumentException("Token is missing");
            }

            if (extractToken.startsWith("Bearer ")) {
                extractToken = extractToken.substring(7);
            }

            JWTVerifier jwtVerifier = JwtVerifierMethod();
            DecodedJWT decodedJWT = jwtVerifier.verify(extractToken);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException ex) {
            System.err.println("Invalid JWT: " + ex.getMessage());
            return null;
        }
    }

    public Long getUserIdFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            JWTVerifier jwtVerifier = JwtVerifierMethod();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asLong();
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    public List<String> getRolesFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            JWTVerifier jwtVerifier = JwtVerifierMethod();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("roles").asList(String.class);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }

    private JWTVerifier JwtVerifierMethod() {
        byte[] decodedKey = Base64.getDecoder().decode(SecurityConstant.SECRET_KEY);
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return JWT.require(algorithm)
                .withIssuer(SecurityConstant.ISSUER)
                .build();
    }

    public boolean isValidToken(String extractToken) {
        JWTVerifier jwtVerifierMethod = JwtVerifierMethod();
        Date issuedAt = jwtVerifierMethod.verify(extractToken).getExpiresAt();
        return !issuedAt.before(new Date());
    }

}
