package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import java.util.List;

public class VehicleModelResponseDTO {


    private Integer modelId;
    private String modelName;
    private Integer isActive;
    private BrandOnlyDTO brandOnlyDTO;

    public BrandOnlyDTO getBrandOnlyDTO() {
        return brandOnlyDTO;
    }

    public void setBrandOnlyDTO(BrandOnlyDTO brandOnlyDTO) {
        this.brandOnlyDTO = brandOnlyDTO;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
