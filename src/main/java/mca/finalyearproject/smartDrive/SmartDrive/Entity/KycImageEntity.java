package mca.finalyearproject.smartDrive.SmartDrive.Entity;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.KycImageType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.TransportType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_images")
public class KycImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type")
    private KycImageType imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kyc_id", nullable = false)
    private UserKycDetailsEntity kyc;

    @Column(name = "original_file_name", nullable = false, length = 255)
    private String originalFileName;

    @Column(name = "alternate_file_name", length = 255)
    private String alternateFileName;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "is_status")
    private Boolean isStatus;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public KycImageType getImageType() {
        return imageType;
    }

    public void setImageType(KycImageType imageType) {
        this.imageType = imageType;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public UserKycDetailsEntity getKyc() {
        return kyc;
    }

    public void setKyc(UserKycDetailsEntity kyc) {
        this.kyc = kyc;
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
        return isStatus;
    }

    public void setStatus(Boolean status) {
        isStatus = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}