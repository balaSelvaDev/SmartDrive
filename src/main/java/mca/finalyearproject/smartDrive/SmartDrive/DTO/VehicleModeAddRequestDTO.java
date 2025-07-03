package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class VehicleModeAddRequestDTO {

    private String modelName;
    private Integer brandId;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
