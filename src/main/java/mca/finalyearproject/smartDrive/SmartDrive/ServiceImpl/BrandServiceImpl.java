package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BrandRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.GlobalStatusType;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl
        //implements BrandService
{

    @Autowired
    private BrandRepository brandRepository;


    public BrandDTO createBrand(BrandDTO dto) {
        BrandEntity brand = dtoToEntity(dto);
        BrandEntity saved = brandRepository.save(brand);
        return entityToDto(saved);
    }


    public BrandDTO updateBrand(BrandDTO dto) {
        BrandEntity brand = dtoToEntity(dto);
        BrandEntity updated = brandRepository.save(brand);
        System.out.println(updated.getBrandId());
        System.out.println(updated.getIsActive());
        System.out.println(updated.getBrandName());
        return entityToDto(updated);
    }


    @Transactional
    public void deleteBrand(Integer brandId) {
        BrandEntity brandEntity = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("Brand not found"));
        brandEntity.setIsActive(GlobalStatusType.DELETE);
        brandRepository.save(brandEntity);
    }


    public PaginationResponse<BrandDTO> getAllBrands(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<BrandEntity> brandList = brandRepository.findByIsActiveNot(GlobalStatusType.DELETE, paging);
        List<BrandDTO> brandDtoList = brandList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        PaginationResponse<BrandDTO> response = new PaginationResponse<>(
                brandDtoList,
                brandList.getNumber(),
                brandList.getTotalPages(),
                brandList.getTotalElements()
        );
        return response;
    }

    public BrandDTO getParticularBrand(Integer brandId) {
        return entityToDto(brandRepository.findById(brandId).get());
    }

    public List<BrandIdNameRequestDTO> getBrandName(String query, int limit) {
        if (query == null || query.trim().isEmpty()) {
            return brandRepository.findAll()
                    .stream().map(this::entitytToBrandIdNameRequestDTO)
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return brandRepository.findByBrandNameContainingIgnoreCase(query)
                .stream()
                .limit(limit)
                .map(this::entitytToBrandIdNameRequestDTO)
                .collect(Collectors.toList());
    }

    public List<BrandIdNameAndVMIdNameResponseDTO> getBrandIdNameAndVehicleModelIdName(String query, int limit) {
        if (query == null || query.trim().isEmpty()) {
            return brandRepository.findAll()
                    .stream().map(this::entitytToBrandIdNameAndVMIdNameRequestDTO)
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return brandRepository.findByBrandNameContainingIgnoreCase(query)
                .stream()
                .limit(limit)
                .map(this::entitytToBrandIdNameAndVMIdNameRequestDTO)
                .collect(Collectors.toList());
    }

    // ============ Mapping Methods ============

    private BrandEntity dtoToEntity(BrandDTO dto) {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(dto.getBrandId());
        brand.setBrandName(dto.getBrandName());
        brand.setIsActive(dto.getIsActive());
        return brand;
    }

    private BrandDTO entityToDto(BrandEntity brand) {
        BrandDTO dto = new BrandDTO();
        dto.setBrandId(brand.getBrandId());
        dto.setBrandName(brand.getBrandName());
        dto.setIsActive(brand.getIsActive());
//        dto.setCreatedDateTime(brand.getCreatedDateTime());
//        dto.setLastUpdatedDateTime(brand.getLastUpdatedDateTime());
        dto.setVehicleModelDTO(new ArrayList<>());
        List<VehicleModelEntity> vehicleModelDTOList = brand.getVehicleModel();
        if(vehicleModelDTOList != null) {
            List<VehicleModelDTO> collect = brand.getVehicleModel()
                    .stream()
                    .map(this::vehicleModelEntityToDto)
                    .collect(Collectors.toList());
            dto.setVehicleModelDTO(collect);
        }
        return dto;
    }

    private VehicleModelDTO vehicleModelEntityToDto(VehicleModelEntity entities) {
        VehicleModelDTO vehicleModelDTO = new VehicleModelDTO();
        vehicleModelDTO.setModelId(entities.getModelId());
        vehicleModelDTO.setModelName(entities.getModelName());
        vehicleModelDTO.setIsActive(entities.getIsActive());
        return vehicleModelDTO;
    }

    private BrandIdNameRequestDTO entitytToBrandIdNameRequestDTO(BrandEntity entity) {
        BrandIdNameRequestDTO dto = new BrandIdNameRequestDTO();
        dto.setBrandId(entity.getBrandId());
        dto.setBrandName(entity.getBrandName());
        return dto;
    }

    private BrandIdNameAndVMIdNameResponseDTO entitytToBrandIdNameAndVMIdNameRequestDTO(BrandEntity entity) {
        BrandIdNameAndVMIdNameResponseDTO dto = new BrandIdNameAndVMIdNameResponseDTO();
        dto.setBrandId(entity.getBrandId());
        dto.setBrandName(entity.getBrandName());
        if(entity.getVehicleModel() != null) {
            List<VehicleModelIdNameResponseDTO> vmList = entity.getVehicleModel().stream().map(this::entityToVMIdNameResponseDTO).collect(Collectors.toList());
            dto.setVehicleModelIdNameDTO(vmList);
        }
        return dto;
    }

    private VehicleModelIdNameResponseDTO entityToVMIdNameResponseDTO(VehicleModelEntity entity) {
        VehicleModelIdNameResponseDTO dto = new VehicleModelIdNameResponseDTO();
        dto.setVehicleModelId(entity.getModelId());
        dto.setVehicleModelName(entity.getModelName());
        return dto;
    }
}