package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.RegistrationVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface RegistrationVerificationRepository extends JpaRepository<RegistrationVerificationEntity, Integer> {

    Boolean existsByUuidAndCode(String uuid, Integer code);

    @Query("SELECT CASE WHEN COUNT(rv) > 0 THEN TRUE ELSE FALSE END " +
            "FROM RegistrationVerificationEntity rv " +
            "WHERE rv.expiryTime > :currentTime")
    Boolean existsValidByUuidAndCode(LocalDateTime currentTime);

    @Transactional
    @Modifying
    @Query("DELETE FROM RegistrationVerificationEntity rv WHERE rv.userId = :userId AND rv.uuid = :uuid")
    void deleteByIdAndUuid(Integer userId, String uuid);

    RegistrationVerificationEntity findByUuidAndCode(String uuid, Integer code);

    Boolean existsByUserIdAndUuid(Integer id, String uuid);

}
