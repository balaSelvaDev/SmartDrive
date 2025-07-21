package mca.finalyearproject.smartDrive.SmartDrive.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "client_location")
public class ClientLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_location_id")
    private Integer clientLocationId;

    @Column(name = "display_name", nullable = false, length = 45)
    private String displayName;

    @Column(name = "location_name", nullable = false, length = 45)
    private String locationName;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "pincode", nullable = false)
    private Integer pincode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "district_id")
    private Integer districtId;

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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
