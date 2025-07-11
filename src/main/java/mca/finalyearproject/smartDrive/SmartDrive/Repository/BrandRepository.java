package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {

    @Query("SELECT i FROM BrandEntity i WHERE i.isActive = 1 AND LOWER(i.brandName) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<BrandEntity> findByBrandNameContainingIgnoreCase(String search);

}
