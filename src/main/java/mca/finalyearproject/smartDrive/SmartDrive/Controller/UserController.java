package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private UtilityClass utilityClass;

    @PostMapping
    public RegistrationVerificationDTO createUserByUser(@RequestBody UserCreateRequestDTO userListDtO, HttpServletRequest request) {
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
    public boolean generatePassword(@RequestBody GeneratePasswordRequestDTO requestDTO, HttpServletRequest request) {
        boolean result = userService.generatePassword(requestDTO);
        if (result) {
            System.out.println("-------------------------");
            System.out.println("websocket notification...");
            String authHeader = request.getHeader("Authorization");
            utilityClass.sendNotificationToAdmin(authHeader, requestDTO.getUserId());
            System.out.println("password completion...");
            System.out.println("-------------------------");
        }
        return result;
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

    @PostMapping(value = "/user-admin/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserKycDetailsEntity editUserByAdmin(@ModelAttribute EditUserCreateByAdminRequestDTO dto,
                                                @ModelAttribute(name = "imageKeyName") ArrayList<String> imageKeyName,
                                                @RequestParam(required = false, name = "profileImage") MultipartFile profileImage,
                                                @RequestParam(required = false) MultipartFile drivingLicenseImage,
                                                @RequestParam(required = false) List<MultipartFile> idProofFiles,
                                                HttpServletRequest request) throws IOException {
        UserKycDetailsEntity userKycDetailsEntity = userService.editeUserByAdmin(dto, imageKeyName, profileImage, drivingLicenseImage, idProofFiles);
        System.out.println("-------------------------");
        System.out.println("websocket notification...");
        String authHeader = request.getHeader("Authorization");
        utilityClass.sendNotificationToAdmin(authHeader, userKycDetailsEntity.getUser().getUserId());
        System.out.println("edit users......");
        System.out.println("-------------------------");
        return userKycDetailsEntity;
    }

}
