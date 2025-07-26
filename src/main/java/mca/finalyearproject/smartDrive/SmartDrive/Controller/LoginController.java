package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginCredentialRepository;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
//@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping
    public ResponseEntity<UserListEntity> createBrand(@RequestBody LoginRequestDTO requestDTO) {
        authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmailId(), requestDTO.getPassword()));
        Optional<UserListEntity> userListEntity = userService.findByEmail(requestDTO.getEmailId());
        Optional<LoginCredentialEntity> loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity.get());
        String genereteJwtToken = jwtProvider.genereteJwtToken(new UserPrincipal(userListEntity.get(), loginCredentialEntity.get()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
        return new ResponseEntity<UserListEntity>(userListEntity.get(), httpHeaders, HttpStatus.OK);
        // return loginService.loginCheck(requestDTO);
    }

    // as of now, i have planned to not used
    @PostMapping("/verification-code")
    public Boolean checkVerificationCode(@RequestBody LoginVerificationCodeRequestDTO requestDTO) {
        return loginService.checkVerificationCode1(requestDTO);
    }

    // as of now, i have planned to not used
    @PostMapping("/reset-verification-code")
    public LoginResponseDTO resetVerificationCode(@RequestBody ResetDetailsResponseDTO requestDTO) {
        return loginService.resetVerificationCode(requestDTO);
    }

}
