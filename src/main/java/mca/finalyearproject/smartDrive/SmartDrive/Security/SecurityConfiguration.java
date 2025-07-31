package mca.finalyearproject.smartDrive.SmartDrive.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomOncePerRequestFilter customOncePerRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // ✅ Add this line
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // ⬇️ Public URLs
                .antMatchers(SecurityConstant.PUBLIC_URL).permitAll()

                // ⬇️ Admin-only endpoints
//                .antMatchers("/api/admin/**").hasRole("ADMIN")

                // ⬇️ User-only endpoints
//                .antMatchers("/api/user/**").hasRole("USER")

                // ⬇️ Any other request requires authentication
//                .anyRequest().authenticated()
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(customOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("selva").password(bCryptPasswordEncoder.encode("1234")).roles("USER");
        auth.authenticationProvider(customAuthenticationProvider);
    }

}
