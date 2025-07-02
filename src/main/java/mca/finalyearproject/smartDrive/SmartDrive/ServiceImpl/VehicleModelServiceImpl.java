package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandOnlyDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelServiceImpl {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public List<VehicleModelResponseDTO> getAllVehicleModels() {
        return vehicleModelRepository.findAll().stream().map(VehicleModelServiceImpl::entityToDTO).collect(Collectors.toList());
    }


    public VehicleModelResponseDTO getVehicleModelById(Integer modelId) {
        return entityToDTO(vehicleModelRepository.findById(modelId).get());
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
