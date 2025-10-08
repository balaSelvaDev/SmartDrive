package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.Role;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginCredentialRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.RoleRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Security.CustomAuthenticationProvider;
import mca.finalyearproject.smartDrive.SmartDrive.Security.JWTProvider;
import mca.finalyearproject.smartDrive.SmartDrive.Security.SecurityConstant;
import mca.finalyearproject.smartDrive.SmartDrive.Security.UserPrincipal;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private LoginCredentialRepository loginCredentialRepository;


    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/admin-login")
    public ResponseEntity<LoginCredentialEntity> loginForAdmin(@RequestBody LoginRequestDTO requestDTO) {
        try {
            Optional<UserListEntity> userListEntity = userService.findByEmail(requestDTO.getEmailId());
            Optional<LoginCredentialEntity> loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity.get());
            Role role = roleRepository.findById(loginCredentialEntity.get().getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            if (loginCredentialEntity.get().getAdminAccess()) {
                authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmailId(), requestDTO.getPassword()));
                String genereteJwtToken = jwtProvider.generateJwtToken(new UserPrincipal(userListEntity.get(), loginCredentialEntity.get()));
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
                return new ResponseEntity<>(loginCredentialEntity.get(), httpHeaders, HttpStatus.OK);
            } else {
                System.out.println("role didn't match...");
                throw new UsernameNotFoundException("Role didn't match...");
            }
        } catch (Exception ex) {
            System.out.println("User not found...");
            throw new UsernameNotFoundException("User not found...");
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> createBrand(@RequestBody LoginRequestDTO requestDTO) {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmailId(), requestDTO.getPassword()));
        Optional<UserListEntity> userListEntity = userService.findByEmail(requestDTO.getEmailId());
        Optional<LoginCredentialEntity> loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity.get());
        String genereteJwtToken = jwtProvider.generateJwtToken(new UserPrincipal(userListEntity.get(), loginCredentialEntity.get()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
        return new ResponseEntity<>(true, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/google/generate-jwt-token")
    public ResponseEntity<?> generate(@RequestBody LoginRequestDTO requestDTO) {
        UserListEntity userListEntity = userService.findByEmail(requestDTO.getEmailId()).orElseThrow(() -> new RuntimeException("UserListEntity not found for user"));
        LoginCredentialEntity loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity).orElseThrow(() -> new RuntimeException("LoginCredentialEntity not found for user"));
        String genereteJwtToken = jwtProvider.generateJwtToken(new UserPrincipal(userListEntity, loginCredentialEntity));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
        return new ResponseEntity<>("Success", httpHeaders, HttpStatus.OK);
    }

}
