package mca.finalyearproject.smartDrive.SmartDrive.DTO;


import java.util.List;

public class BrandDTO {

    private Integer brandId;
    private String brandName;
    private Integer isActive;
    private List<VehicleModelDTO> vehicleModelDTO;

    public List<VehicleModelDTO> getVehicleModelDTO() {
        return vehicleModelDTO;
    }

    public void setVehicleModelDTO(List<VehicleModelDTO> vehicleModelDTO) {
        this.vehicleModelDTO = vehicleModelDTO;
    }


    // Getters & Setters
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}