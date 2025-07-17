package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class BrandIdNameVMIdNameResponseDTO {

    private Integer brandId;
    private String brandName;
    private Integer vehicleModelId;
    private String vehicleModelName;

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

    public Integer getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Integer vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }
}
