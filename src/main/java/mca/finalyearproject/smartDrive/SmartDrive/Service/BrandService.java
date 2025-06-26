package mca.finalyearproject.smartDrive.SmartDrive.Service;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;

import java.util.List;

public interface BrandService {
    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(BrandDTO brandDTO);
    void deleteBrand(Integer brandId);
    List<BrandDTO> getAllBrands();
}