package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {



}
