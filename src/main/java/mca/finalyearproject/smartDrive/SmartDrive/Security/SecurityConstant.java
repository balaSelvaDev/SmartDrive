package mca.finalyearproject.smartDrive.SmartDrive.Security;

public class SecurityConstant {

    public static final String ISSUER = "SmartDrive booking hub";
    public static final String SECRET_KEY = "$secretKey#";
    public static final Integer TOKEN_EXPIRE_TIME = 1 * 60 * 60 * 1000;
    //    public static final String[] PUBLIC_URL = {"/api/user/login", "/api/user/register"};
    public static final String[] PUBLIC_URL = {
            "/api/login/**", "/api/login",
            "/api/users/**",
            "/api/vehicle", "/api/vehicle/**",
            "/api/brands", "/api/brands/**",
            "/oauth2/authorization/google",
            "/api/booking/**",
            "/api/client-location/**",
            "/ws/**", "/topic/**"
    };
    public static final String HEADER_NAME = "Authorization";
    public static final String BEARER_NAME = "Bearer ";

}
