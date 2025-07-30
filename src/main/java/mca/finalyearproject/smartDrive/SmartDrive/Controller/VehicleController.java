package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleAvailabilityDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @GetMapping("/customer")
    public ResponseEntity<PaginationResponse<VehicleAvailabilityDTO>> getAllVehicleForCustomer(@RequestParam(defaultValue = "0") int page,
                                                                                                     @RequestParam(defaultValue = "5") int size,
                                                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime userPickupDatetime,
                                                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime userDropDatetime,
                                                                                                     @RequestParam Boolean isVisibleOnline,
                                                                                                     @RequestParam String vehicleStatus) {
        return ResponseEntity.ok(vehicleService.getAllVehicleForCustomer(page, size, userPickupDatetime, userDropDatetime, isVisibleOnline, vehicleStatus));
    }

}
