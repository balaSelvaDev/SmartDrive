package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameAndVMIdNameResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BrandServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    @GetMapping
    public ResponseEntity<PaginationResponse<BrandDTO>> getAllBrands(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(brandService.getAllBrands(page, size));
    }

    @GetMapping("/{brandId}")
    public BrandDTO getParticularBrand(@PathVariable("brandId") Integer brandId) {
        return brandService.getParticularBrand(brandId);
    }

    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }

    @PutMapping
    public BrandDTO updateBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(brandDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
    }

    @GetMapping("/brand-name")
    public List<BrandIdNameRequestDTO> getBrandName(@RequestParam(required = false) String search,
                                                    @RequestParam(defaultValue = "5") int limit) {
        return brandService.getBrandName(search, limit);
    }

    @GetMapping("/search/brandname")
    public List<BrandIdNameAndVMIdNameResponseDTO> getBrandIdNameAndVehicleModelIdName(@RequestParam(required = false, value = "brandname") String brandname,
                                                                                        @RequestParam(defaultValue = "5") int limit) {
        return brandService.getBrandIdNameAndVehicleModelIdName(brandname, limit);
    }



}