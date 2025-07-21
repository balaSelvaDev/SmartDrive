package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import javax.persistence.Column;

public class ClientLocationResponseDTO {

    private Integer clientLocationId;
    private String displayName;
    private String locationName;
    private String address;
    private Integer pincode;
    private Boolean isActive;

    public Integer getClientLocationId() {
        return clientLocationId;
    }

    public void setClientLocationId(Integer clientLocationId) {
        this.clientLocationId = clientLocationId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
