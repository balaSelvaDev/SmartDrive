package mca.finalyearproject.smartDrive.SmartDrive.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String emailId = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername(emailId);
//		System.out.println("000");
		if (loadUserByUsername != null) {
//			System.out.println("001");
			if (bCryptPasswordEncoder.matches(password, loadUserByUsername.getPassword())) {
//				System.out.println("002");
				return new UsernamePasswordAuthenticationToken(password, loadUserByUsername);
			} else {
//				System.out.println("003");
				throw new UsernameNotFoundException("Username/Password not found..");
			}
		}
//		System.out.println("004");
		throw new UsernameNotFoundException("User name not found..");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
