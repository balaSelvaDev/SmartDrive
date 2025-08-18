package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BrandRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.GlobalStatusType;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelServiceImpl {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public PaginationResponse<VehicleModelResponseDTO> getAllVehicleModels(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<VehicleModelEntity> vehicleModelList = vehicleModelRepository.findAllWithBrand(paging, GlobalStatusType.DELETE);
        List<VehicleModelResponseDTO> vehicleModelDtoList = vehicleModelList.stream()
                .map(VehicleModelServiceImpl::entityToDTO)
                .collect(Collectors.toList());
        PaginationResponse<VehicleModelResponseDTO> response = new PaginationResponse<>(
                vehicleModelDtoList,
                vehicleModelList.getNumber(),
                vehicleModelList.getTotalPages(),
                vehicleModelList.getTotalElements()
        );
        return response;
    }

    public VehicleModelResponseDTO getVehicleModelById(Integer modelId) {
        return entityToDTO(vehicleModelRepository.findById(modelId).get());
    }

    public VehicleModelResponseDTO addVehicleModel(VehicleModeAddRequestDTO dto) {
        VehicleModelEntity save = vehicleModelRepository.save(DTOToEntity(dto));
        return entityToDTO(save);
    }

    public VehicleModelResponseDTO updateVehicleModel(Integer vehicleModelId, VehicleModeUpdateRequestDTO dto) {
        VehicleModelEntity entity = vehicleModelRepository.findById(vehicleModelId).orElseThrow(() -> new RuntimeException("Not found"));

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

    @Transactional
    public void deleteVehicleModel(Integer vehicleModel) {
        VehicleModelEntity vehicleModelEntity = vehicleModelRepository.findById(vehicleModel).orElseThrow(() -> new RuntimeException("Vehicle model not found"));
        vehicleModelEntity.setIsActive(GlobalStatusType.DELETE);
        vehicleModelRepository.save(vehicleModelEntity);
    }

}
