package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import java.util.List;

public class BrandIdNameAndVMIdNameResponseDTO {

    private Integer brandId;
    private String brandName;
    private List<VehicleModelIdNameResponseDTO> vehicleModelIdNameDTO;

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

    public List<VehicleModelIdNameResponseDTO> getVehicleModelIdNameDTO() {
        return vehicleModelIdNameDTO;
    }

    public void setVehicleModelIdNameDTO(List<VehicleModelIdNameResponseDTO> vehicleModelIdNameDTO) {
        this.vehicleModelIdNameDTO = vehicleModelIdNameDTO;
    }
}
