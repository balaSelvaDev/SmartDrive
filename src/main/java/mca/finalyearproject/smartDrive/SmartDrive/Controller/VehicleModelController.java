package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import lombok.RequiredArgsConstructor;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-models")
public class VehicleModelController {

    @Autowired
    private VehicleModelServiceImpl vehicleModelService;

    @GetMapping
    public List<VehicleModelEntity> getAllModels() {
        return vehicleModelService.getAllVehicleModels();
    }

    @GetMapping("/{id}")
    public VehicleModelEntity getModelById(@PathVariable Integer id) {
        return vehicleModelService.getVehicleModelById(id);
    }

}
