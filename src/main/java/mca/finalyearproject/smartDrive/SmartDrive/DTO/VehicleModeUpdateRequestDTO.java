package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class VehicleModeUpdateRequestDTO {

    private String modelName;
    private Integer brandId;
    private Integer isActive;

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

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

    @Override
    public String toString() {
        return "VehicleModeUpdateRequestDTO{" +
                "modelName='" + modelName + '\'' +
                ", brandId=" + brandId +
                ", isActive=" + isActive +
                '}';
    }
}
