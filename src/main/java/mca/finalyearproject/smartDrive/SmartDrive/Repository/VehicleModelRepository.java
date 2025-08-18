package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModelEntity, Integer> {

//    @Query("SELECT vm FROM VehicleModelEntity vm LEFT JOIN vm.brand")
//    Page<VehicleModelEntity> findAllWithBrand(Pageable pageable);
        @Query(
                value = "SELECT vm FROM VehicleModelEntity vm LEFT JOIN FETCH vm.brand where vm.isActive != :isActive ",
                countQuery = "SELECT count(vm) FROM VehicleModelEntity vm where vm.isActive != :isActive",
                nativeQuery = false
        )
        Page<VehicleModelEntity> findAllWithBrand(Pageable pageable, Integer isActive);

}
