package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import javax.persistence.Column;

public class VehicleImageResponseDTO {

    private Integer imageId;
    private Integer imageType;
    private String originalFileName;
    private String alternateFileName;
    private String imageUrl;
    private Boolean status;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
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
