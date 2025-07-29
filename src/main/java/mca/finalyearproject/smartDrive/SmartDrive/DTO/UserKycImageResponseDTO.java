package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.KycImageType;
import mca.finalyearproject.smartDrive.SmartDrive.Util.ImageType;

public class UserKycImageResponseDTO {

    private KycImageType imageType;
    private String originalFileName;
    private String alternateFileName;
    private String imageUrl;
    private Boolean status;

    public KycImageType getImageType() {
        return imageType;
    }

    public void setImageType(KycImageType imageType) {
        this.imageType = imageType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getAlternateFileName() {
        return alternateFileName;
    }

    public void setAlternateFileName(String alternateFileName) {
        this.alternateFileName = alternateFileName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
