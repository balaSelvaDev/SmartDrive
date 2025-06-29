package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
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
