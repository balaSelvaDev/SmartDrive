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


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ResponseEntity<LoginCredentialEntity> createBrand(@RequestBody LoginRequestDTO requestDTO) {
//        System.out.println(requestDTO);
//        System.out.println("<<<1>>>");
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmailId(), requestDTO.getPassword()));
//        System.out.println("<<<2>>>");
        Optional<UserListEntity> userListEntity = userService.findByEmail(requestDTO.getEmailId());
//        System.out.println("<<<3>>>");
        Optional<LoginCredentialEntity> loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity.get());
//        System.out.println("<<<4>>>");
        String genereteJwtToken = jwtProvider.generateJwtToken(new UserPrincipal(userListEntity.get(), loginCredentialEntity.get()));
//        System.out.println("<<<5>>>");
        HttpHeaders httpHeaders = new HttpHeaders();
//        System.out.println("<<<6>>>");
        httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
//        System.out.println("<<<7>>>");
        return new ResponseEntity<>(loginCredentialEntity.get(), httpHeaders, HttpStatus.OK);
//         return loginService.loginCheck(requestDTO);
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

//    @GetMapping("/login/google")
//    public ResponseEntity<String > loginGoogleAuth(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/oauth2/authorization/google");
//        return ResponseEntity.ok("Redirecting ..");
//    }
//    @GetMapping("/loginSuccess")
//    public ResponseEntity<? > handleGoogleSuccess(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//        User user = userService.loginRegisterByGoogleOAuth2(oAuth2AuthenticationToken);
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:3000/home")).build();
//    }

    @PostMapping("/google/generate-jwt-token")
    public ResponseEntity<?> generate(@RequestBody LoginRequestDTO requestDTO) {
//        authenticationProvider
//                .authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmailId(), requestDTO.getPassword()));
        UserListEntity userListEntity = userService.findByEmail(requestDTO.getEmailId()).orElseThrow(() -> new RuntimeException("UserListEntity not found for user"));
        LoginCredentialEntity loginCredentialEntity = loginCredentialRepository.findByUser(userListEntity).orElseThrow(() -> new RuntimeException("LoginCredentialEntity not found for user"));
        String genereteJwtToken = jwtProvider.generateJwtToken(new UserPrincipal(userListEntity, loginCredentialEntity));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.HEADER_NAME, genereteJwtToken);
        return new ResponseEntity<>("Success", httpHeaders, HttpStatus.OK);
        // return loginService.loginCheck(requestDTO);
    }

}
