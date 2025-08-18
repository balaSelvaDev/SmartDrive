package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModeAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModeUpdateRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleModelServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-models")
public class VehicleModelController {

    @Autowired
    private VehicleModelServiceImpl vehicleModelService;

    @GetMapping
    public ResponseEntity<PaginationResponse<VehicleModelResponseDTO>> getAllVehicleModels(@RequestParam(defaultValue = "0") int page,
                                                                                           @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(vehicleModelService.getAllVehicleModels(page, size));
    }

    @GetMapping("/{id}")
    public VehicleModelResponseDTO getModelById(@PathVariable("id") Integer id) {
        return vehicleModelService.getVehicleModelById(id);
    }

    @PostMapping
    public VehicleModelResponseDTO addVehicleModel(@RequestBody VehicleModeAddRequestDTO dto) {
        return vehicleModelService.addVehicleModel(dto);
    }

    @PutMapping("/{vehicleModelId}")
    public VehicleModelResponseDTO updateVehicleModel(@PathVariable("vehicleModelId") Integer vehicleModelId, @RequestBody VehicleModeUpdateRequestDTO dto) {
        return vehicleModelService.updateVehicleModel(vehicleModelId, dto);
    }

    @DeleteMapping("/{vehicleModelId}")
    public void deleteVehicleModel(@PathVariable("vehicleModelId") Integer brandId) {
        vehicleModelService.deleteVehicleModel(brandId);
    }

}

