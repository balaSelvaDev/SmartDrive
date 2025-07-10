package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
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

}