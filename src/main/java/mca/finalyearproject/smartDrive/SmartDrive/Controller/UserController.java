package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public RegistrationVerificationDTO createBrand(@RequestBody UserCreateRequestDTO userListDtO) {
        return userService.createUser(userListDtO);
    }

    @PostMapping("/verification-code")
    public CheckVerificationCodeResponseDTO checkVerificationCode(@RequestBody VerificationCodeRequestDTO requestDTO) {

        return userService.checkVerificationCode(requestDTO);

    }

    @PostMapping("/generate-password")
    public void generatePassword(@RequestBody GeneratePasswordRequestDTO requestDTO) {

        userService.generatePassword(requestDTO);

    }




}
