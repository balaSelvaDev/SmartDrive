package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Service.BrandService;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands1")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

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

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }
}