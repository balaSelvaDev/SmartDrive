package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.IdProofType;

public class UserCreateByAdminRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String drivingLicenseNumber;

    private IdProofType idProofType; // Using the enum
    private String idProofNumber;

    private String addressLine1;
    private String addressLine2;
    private String taluk;
    private String district;
    private String state;
    private String pincode;
    private String country = "India";

    private String fatherName;
    private String motherName;

    private String nomineeName;
    private String nomineeRelation;
    private String nomineePhone;

    private String occupation;
    private String companyName;

    private String alternatePhoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "UserCreateByAdminRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", idProofType=" + idProofType +
                ", idProofNumber='" + idProofNumber + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", taluk='" + taluk + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", country='" + country + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                ", nomineeRelation='" + nomineeRelation + '\'' +
                ", nomineePhone='" + nomineePhone + '\'' +
                ", occupation='" + occupation + '\'' +
                ", companyName='" + companyName + '\'' +
                ", alternatePhoneNumber='" + alternatePhoneNumber + '\'' +
                '}';
    }
}
