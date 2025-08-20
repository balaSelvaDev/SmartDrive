package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.KycImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface KycImageRepository extends JpaRepository<KycImageEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE kyc_images SET is_status = 0 WHERE kyc_id = :kycId and image_type = :imageType ", nativeQuery = true)
    void updateUserKycInactiveIsStatusByKycId(Integer kycId, String imageType);

}