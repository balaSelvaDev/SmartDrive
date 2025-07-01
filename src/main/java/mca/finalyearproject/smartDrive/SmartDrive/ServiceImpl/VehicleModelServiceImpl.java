package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import lombok.RequiredArgsConstructor;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelServiceImpl {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public List<VehicleModelEntity> getAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }


    public VehicleModelEntity getVehicleModelById(Integer modelId) {
        return vehicleModelRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Vehicle model not found with id: " + modelId));
    }

}
