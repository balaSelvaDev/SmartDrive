package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return entityToDto(updated);
    }


    public void deleteBrand(Integer brandId) {
        brandRepository.deleteById(brandId);
    }


    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public BrandDTO getParticularBrand(Integer brandId) {
        return entityToDto(brandRepository.findById(brandId).get());
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
        vehicleModelDTO.setIsActive(entities.getActive());
        return vehicleModelDTO;
    }
}