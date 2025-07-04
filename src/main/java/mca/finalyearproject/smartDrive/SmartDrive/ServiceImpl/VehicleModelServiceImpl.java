package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandOnlyDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModeAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModeUpdateRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BrandRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelServiceImpl {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<VehicleModelResponseDTO> getAllVehicleModels() {
        return vehicleModelRepository.findAll().stream().map(VehicleModelServiceImpl::entityToDTO).collect(Collectors.toList());
    }

    public VehicleModelResponseDTO getVehicleModelById(Integer modelId) {
        return entityToDTO(vehicleModelRepository.findById(modelId).get());
    }

    public VehicleModelResponseDTO addVehicleModel(VehicleModeAddRequestDTO dto) {
        VehicleModelEntity save = vehicleModelRepository.save(DTOToEntity(dto));
        return entityToDTO(save);
    }

    public VehicleModelResponseDTO updateVehicleModel(Integer vehicleModelId, VehicleModeUpdateRequestDTO dto) {
        VehicleModelEntity entity = vehicleModelRepository.findById(vehicleModelId).orElseThrow(()-> new RuntimeException("Not found"));

        BrandEntity brandEntity = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found in db"));
        entity.setModelName(dto.getModelName());
        entity.setIsActive(dto.getIsActive());
        entity.setBrand(brandEntity);

        return entityToDTO(entity);
    }

    public VehicleModelEntity DTOToEntity(VehicleModeAddRequestDTO dto) {
        VehicleModelEntity entity = new VehicleModelEntity();
        BrandEntity brandEntity = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found in db"));
        entity.setModelName(dto.getModelName());
        entity.setBrand(brandEntity);
        entity.setIsActive(1);
        return entity;
    }

    public static VehicleModelResponseDTO entityToDTO(VehicleModelEntity entity) {
        VehicleModelResponseDTO vehicleModelResponseDTO = new VehicleModelResponseDTO();
        BrandOnlyDTO brandOnlyDTO = new BrandOnlyDTO();
        brandOnlyDTO.setBrandId(entity.getBrand().getBrandId());
        brandOnlyDTO.setBrandName(entity.getBrand().getBrandName());
        brandOnlyDTO.setIsActive(entity.getBrand().getIsActive());
        vehicleModelResponseDTO.setBrandOnlyDTO(brandOnlyDTO);

        vehicleModelResponseDTO.setModelId(entity.getModelId());
        vehicleModelResponseDTO.setModelName(entity.getModelName());
        vehicleModelResponseDTO.setIsActive(entity.getIsActive());

        return vehicleModelResponseDTO;
    }

}
