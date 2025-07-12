package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
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
    public RegistrationVerificationDTO createUserByUser(@RequestBody UserCreateRequestDTO userListDtO) {
        return userService.createUserByUser(userListDtO);
    }

    @PostMapping("/verification-code")
    public CheckVerificationCodeResponseDTO checkVerificationCode(@RequestBody VerificationCodeRequestDTO requestDTO) {
        return userService.checkVerificationCode(requestDTO);
    }

    @PostMapping("/generate-password")
    public void generatePassword(@RequestBody GeneratePasswordRequestDTO requestDTO) {
        userService.generatePassword(requestDTO);
    }

    @PostMapping("/user-admin")
    public UserKycDetailsEntity createUserByAdmin(@RequestBody UserCreateByAdminRequestDTO dto) {
        return userService.createUserByAdmin(dto);
    }




}
