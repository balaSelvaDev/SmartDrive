package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    // as of now, i have planned to not used
    @PostMapping("/verification-code")
    public CheckVerificationCodeResponseDTO checkVerificationCode(@RequestBody VerificationCodeRequestDTO requestDTO) {
        return userService.checkVerificationCode(requestDTO);
    }

    // as of now, i have planned to not used
    @PostMapping("/reset-verification-code")
    public RegistrationVerificationDTO resetVerificationCode(@RequestParam Integer userId,
                                                             @RequestParam String emailId) {
        return userService.resetVerificationCode(userId, emailId);
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

    @GetMapping("/individual")
    public ResponseEntity<UserAndKycResponseDTO> getUserAndKycDetailsById(
            @RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(userService.getUserAndKycDetailsById(userId));
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<UserAndKycResponseDTO>> getUserAndKycDetails(@RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getUserAndKycDetails(page, size));
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<UserListResponseDTO> getUserListForCustomer(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserListForCustomer(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUserList(@PathVariable("userId") Integer userId) {
        userService.deleteUserList(userId);
    }

}
