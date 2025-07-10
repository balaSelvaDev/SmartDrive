package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserKycDetailsAddDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserKycDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/kyc")
public class UserKycDetailsController {

    @Autowired
    private UserKycDetailsService kycService;

//    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(value = "/add")
    public ResponseEntity<String> addKyc(
            @RequestBody UserKycDetailsAddDTO dto
//            , @RequestParam("files") MultipartFile[] files
    ) {
        kycService.saveKycDetails(dto);
        return ResponseEntity.ok("KYC Details saved successfully.");
    }
}