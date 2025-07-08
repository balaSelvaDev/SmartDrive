package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
//@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping
    public LoginResponseDTO createBrand(@RequestBody LoginRequestDTO requestDTO) {
        return loginService.loginCheck(requestDTO);
    }

    @PostMapping("/verification-code")
    public Boolean checkVerificationCode(@RequestBody LoginVerificationCodeRequestDTO requestDTO) {

        return loginService.checkVerificationCode1(requestDTO);

    }

}
