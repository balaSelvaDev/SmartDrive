package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public boolean generatePassword(@RequestBody GeneratePasswordRequestDTO requestDTO) {
        return userService.generatePassword(requestDTO);
    }

    @PostMapping(value = "/user-admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserKycDetailsEntity createUserByAdmin(@ModelAttribute UserCreateByAdminRequestDTO dto,
                                                  @RequestParam("profileImage") MultipartFile profileImage,
                                                  @RequestParam(required = false) MultipartFile drivingLicenseImage,
                                                  @RequestParam(required = false) List<MultipartFile> idProofFiles) throws IOException {
        return userService.createUserByAdmin(dto, profileImage, drivingLicenseImage, idProofFiles);
    }




}
