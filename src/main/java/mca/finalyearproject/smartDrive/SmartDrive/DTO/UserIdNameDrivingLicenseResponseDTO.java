package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.KycImageEntity;

import java.util.List;

public class UserIdNameDrivingLicenseResponseDTO {

    private Integer userId;
    private String userName;
    private String drivingLicense;
    private List<KyImageResponseDTO> kycImageEntityList;

    public List<KyImageResponseDTO> getKycImageEntityList() {
        return kycImageEntityList;
    }

    public void setKycImageEntityList(List<KyImageResponseDTO> kycImageEntityList) {
        this.kycImageEntityList = kycImageEntityList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
}
