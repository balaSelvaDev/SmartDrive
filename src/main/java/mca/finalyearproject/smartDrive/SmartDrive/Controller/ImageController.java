package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/upload-multiple/{kycId}")
    public ResponseEntity<List<String>> uploadKycImages(
            @RequestParam("types") Integer userId,
            @PathVariable("kycId") Integer kycId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("types") String types
    ) throws IOException {
        List<String> urls = cloudinaryService.uploadImages(userId, kycId, files, types);
        return ResponseEntity.ok(urls);
    }
}