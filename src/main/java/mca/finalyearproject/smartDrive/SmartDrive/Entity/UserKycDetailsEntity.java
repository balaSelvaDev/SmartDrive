package mca.finalyearproject.smartDrive.SmartDrive.Entity;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.IdProofType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_kyc_details")
public class UserKycDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kycId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserListEntity user;

    @Column(name = "driving_license_number", nullable = false, length = 50)
    private String drivingLicenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_proof_type", nullable = false)
    private IdProofType idProofType;

    @Column(name = "id_proof_number", nullable = false, length = 50)
    private String idProofNumber;

    @Column(name = "address_line_1", nullable = false, length = 255)
    private String addressLine1;

    @Column(name = "address_line_2", length = 255)
    private String addressLine2;

    @Column(nullable = false, length = 100)
    private String taluk;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 10)
    private String pincode;

    @Column(length = 100)
    private String country = "India";

    @Column(name = "father_name", nullable = false, length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Column(name = "nominee_name", length = 100)
    private String nomineeName;

    @Column(name = "nominee_relation", length = 50)
    private String nomineeRelation;

    @Column(name = "nominee_phone", length = 15)
    private String nomineePhone;

    @Column(length = 100)
    private String occupation;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "alternate_phone_number", length = 15)
    private String alternatePhoneNumber;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kyc", cascade = CascadeType.ALL)
    private List<KycImageEntity> kycImage;

    public List<KycImageEntity> getKycImage() {
        return kycImage;
    }

    public void setKycImage(List<KycImageEntity> kycImage) {
        this.kycImage = kycImage;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getKycId() {
        return kycId;
    }

    public void setKycId(Integer kycId) {
        this.kycId = kycId;
    }

    public UserListEntity getUser() {
        return user;
    }

    public void setUser(UserListEntity user) {
        this.user = user;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public IdProofType getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(IdProofType idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getNomineeRelation() {
        return nomineeRelation;
    }

    public void setNomineeRelation(String nomineeRelation) {
        this.nomineeRelation = nomineeRelation;
    }

    public String getNomineePhone() {
        return nomineePhone;
    }

    public void setNomineePhone(String nomineePhone) {
        this.nomineePhone = nomineePhone;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
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

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
