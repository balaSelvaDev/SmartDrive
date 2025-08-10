package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    public CustomOncePerRequestFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(SecurityConstant.HEADER_NAME.toLowerCase());

        if (token != null && token.startsWith(SecurityConstant.BEARER_NAME)) {
            String extractToken = token.substring(7);
            if (extractToken != null) {
                String userName = jwtProvider.getSubjectFromToken(extractToken);
                boolean validToken = jwtProvider.isValidToken(extractToken);

                if (validToken) {
                    // Fetch roles from token
                    List<String> roles = jwtProvider.getRolesFromToken(extractToken);

                    // Convert to GrantedAuthority list
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("" + role))
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userName, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
