package mca.finalyearproject.smartDrive.SmartDrive.Security;

import java.util.Optional;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginCredentialRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginCredentialRepository loginCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Optional<UserListEntity> userListEntity = userRepository.findByEmail(emailId);
        Optional<LoginCredentialEntity> loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity.get());
        if (userListEntity.isPresent() && loginCredentialEntity.isPresent()) {
            return new UserPrincipal(userListEntity.get(), loginCredentialEntity.get());
        }
        System.out.println("---> 19");
        throw new UsernameNotFoundException("User name not found..");
    }

}
