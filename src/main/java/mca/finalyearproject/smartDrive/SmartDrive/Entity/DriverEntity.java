package mca.finalyearproject.smartDrive.SmartDrive.Entity;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.EmploymentType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.Gender;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.MarritalStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver")
public class DriverEntity {

    @Id
    @Column(name = "driver_id", length = 255)
    private String driverId;

    @Column(name = "driver_name", nullable = false, length = 100)
    private String driverName;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(name = "total_experience", nullable = false)
    private Integer totalExperience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EmploymentType type;

    @Column(name = "joined_date", nullable = false)
    private LocalDateTime joinedDate;

    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "nominee_name", length = 100)
    private String nomineeName;

    @Column(name = "nominee_phone_number", length = 15)
    private String nomineePhoneNumber;

    @Column(length = 255)
    private String address1;

    @Column(length = 255)
    private String address2;

    @Enumerated(EnumType.STRING)
    @Column(name = "marrital_status", length = 10)
    private MarritalStatus marritalStatus;

    @Column(name = "recommended_person_name", length = 100)
    private String recommendedPersonName;

    @Column(name = "recommended_person_contact_no", length = 15)
    private String recommendedPersonContactNo;

    @Column(name = "is_status")
    private Boolean isStatus = true;

    @Column(name = "reliving_date")
    private LocalDateTime relivingDate;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    @Column(name = "driving_license_number", nullable = false, unique = true, length = 100)
    private String drivingLicenseNumber;

    @Column(name = "driving_license_image_url", length = 500)
    private String drivingLicenseImageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDrivingLicenseImageUrl() {
        return drivingLicenseImageUrl;
    }

    public void setDrivingLicenseImageUrl(String drivingLicenseImageUrl) {
        this.drivingLicenseImageUrl = drivingLicenseImageUrl;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDateTime getRelivingDate() {
        return relivingDate;
    }

    public void setRelivingDate(LocalDateTime relivingDate) {
        this.relivingDate = relivingDate;
    }

    public Boolean getStatus() {
        return isStatus;
    }

    public void setStatus(Boolean status) {
        isStatus = status;
    }

    public String getRecommendedPersonContactNo() {
        return recommendedPersonContactNo;
    }

    public void setRecommendedPersonContactNo(String recommendedPersonContactNo) {
        this.recommendedPersonContactNo = recommendedPersonContactNo;
    }

    public String getRecommendedPersonName() {
        return recommendedPersonName;
    }

    public void setRecommendedPersonName(String recommendedPersonName) {
        this.recommendedPersonName = recommendedPersonName;
    }

    public MarritalStatus getMarritalStatus() {
        return marritalStatus;
    }

    public void setMarritalStatus(MarritalStatus marritalStatus) {
        this.marritalStatus = marritalStatus;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getNomineePhoneNumber() {
        return nomineePhoneNumber;
    }

    public void setNomineePhoneNumber(String nomineePhoneNumber) {
        this.nomineePhoneNumber = nomineePhoneNumber;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    public EmploymentType getType() {
        return type;
    }

    public void setType(EmploymentType type) {
        this.type = type;
    }

    public Integer getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Integer totalExperience) {
        this.totalExperience = totalExperience;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

}
