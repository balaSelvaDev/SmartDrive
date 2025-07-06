package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserKycDetailsAddDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.UserKycDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kyc")
public class UserKycDetailsController {

    @Autowired
    private UserKycDetailsService kycService;

    @PostMapping("/add")
    public ResponseEntity<String> addKyc(@RequestBody UserKycDetailsAddDTO dto) {
        kycService.saveKycDetails(dto);
        return ResponseEntity.ok("KYC Details saved successfully.");
    }
}