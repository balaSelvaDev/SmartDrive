package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.KycImageEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.KycImageRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserKycDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    String USER_PROFILE_FOLDER_PATH = "Smart-drive-booking-hub/User profile image";
    String PAN_CARD_PATH = "Smart-drive-booking-hub/Pan card";
    String DRIVING_LICENSE_PATH = "Smart-drive-booking-hub/Driving license";
    String AADHAR_CAR_PATH = "Smart-drive-booking-hub/Aadhar card";

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private KycImageRepository kycImageRepository;

    @Autowired
    private UserKycDetailsRepository userKycDetailsRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap(
                "folder", USER_PROFILE_FOLDER_PATH,
                "public_id", "USER_" + 1
        );
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        return uploadResult.get("secure_url").toString(); // Get URL of uploaded image
    }



    public List<String> uploadImages(Integer userId, Integer kycId, MultipartFile[] files, String types) throws IOException {

        UserKycDetailsEntity kyc = userKycDetailsRepository.findById(kycId)
                .orElseThrow(() -> new RuntimeException("KYC ID not found"));

        String[] typeList = types.split(",");
        if (files.length != typeList.length) {
            throw new IllegalArgumentException("Files count and types count must match");
        }

        List<String> uploadedUrls = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String type = typeList[i].trim().toLowerCase();

            long sizeInBytes = file.getSize();
            double sizeInKB = sizeInBytes / 1024.0;
            double sizeInMB = sizeInKB / 1024.0;
            if (sizeInMB > 1.0) {
                throw new IllegalArgumentException("File size exceeds 1MB limit");
            }

            String folder;
            String publicId;

            switch (type) {
                case "profile":
                    folder = "Smart-drive-booking-hub/User profile image";
                    publicId = "USER_PROFILE_" + userId +"_" + kycId;
                    break;
                case "pancard":
                    folder = "Smart-drive-booking-hub/Pan card";
                    publicId = "USER_PANCARD_" + userId +"_" + kycId;
                    break;
                case "aadhar":
                    folder = "Smart-drive-booking-hub/Aadhar card";
                    publicId = "USER_AADHAR_" + userId +"_" + kycId;
                    break;
                case "driving_license":
                    folder = "Smart-drive-booking-hub/Driving license";
                    publicId = "USER_LICENSE_" + userId +"_" + kycId;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid document type: " + type);
            }

            Map<String, Object> options = ObjectUtils.asMap(
                    "folder", folder,
                    "public_id", publicId
//                  ,"overwrite", true,
//                   "resource_type", "auto"
            );

            String originalFilename = file.getOriginalFilename();
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
            String uploadedUrl = (String) uploadResult.get("secure_url");
            KycImageEntity image = new KycImageEntity();
            image.setKyc(kyc);
            image.setOriginalFileName(originalFilename);
            image.setAlternateFileName((String) uploadResult.get("public_id"));
            image.setImageUrl(uploadedUrl);
            image.setStatus(true);

            kycImageRepository.save(image);
            uploadedUrls.add(uploadedUrl);
        }

        return uploadedUrls;
    }

}