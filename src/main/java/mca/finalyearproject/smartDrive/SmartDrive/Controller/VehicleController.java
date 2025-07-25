package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameVMIdNameResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @GetMapping
    public ResponseEntity<PaginationResponse<VehicleResponseDTO>> getAllVehicle(@RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(vehicleService.getAllVehicle(page, size));
    }

    @GetMapping("/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable Integer id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public VehicleEntity getVehicleById(@RequestPart("profileImageList") List<MultipartFile> images,
                                        @RequestPart("data") VehicleAddRequestDTO dto) throws IOException {
        return vehicleService.addVehicle(images, dto);
    }



}
