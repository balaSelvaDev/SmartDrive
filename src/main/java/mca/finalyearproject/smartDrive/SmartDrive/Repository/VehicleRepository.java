package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {

    List<VehicleEntity> findByVehicleNameContainingIgnoreCase(String vehicleName);

}
