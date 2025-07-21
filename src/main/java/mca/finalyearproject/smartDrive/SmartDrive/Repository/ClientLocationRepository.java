package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.ClientLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLocationRepository extends JpaRepository<ClientLocationEntity, Integer> {

    List<ClientLocationEntity> findByIsActiveAndDistrictId(boolean status, Integer districtId);

}
