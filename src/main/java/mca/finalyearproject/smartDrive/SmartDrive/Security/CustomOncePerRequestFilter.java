package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
//		System.out.println("CustomOncePerRequestFilter::doFilterInternal");
		String token = request.getHeader(SecurityConstant.HEADER_NAME);
		if (token != null && token.startsWith(SecurityConstant.BEARER_NAME)) {
//			System.out.println("TOKEN:: " + token);
			String extractToken = token.substring(7);
//			System.out.println(extractToken);
			if (extractToken != null) {
//				System.out.println("1");
				String userName = jwtProvider.getSubjectFromToken(extractToken);
				boolean validToken = jwtProvider.isValidToken(extractToken);
//				System.out.println("2");
				if (validToken) {
//					System.out.println("3");
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userName, null, null);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}
		doFilter(request, response, filterChain);

	}

}
