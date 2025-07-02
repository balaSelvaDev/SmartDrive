package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @GetMapping
    public List<VehicleResponseDTO> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable Integer id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public VehicleEntity getVehicleById(@RequestBody VehicleAddRequestDTO dto) {
        return vehicleService.addVehicle(dto);
    }

}
