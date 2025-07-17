package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import javax.persistence.Column;

public class KyImageResponseDTO {

    private String originalFileName;
    private String alternateFileName;
    private String imageUrl;
    private Boolean isStatus;

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
        return isStatus;
    }

    public void setStatus(Boolean status) {
        isStatus = status;
    }
}
