package mca.finalyearproject.smartDrive.SmartDrive.Repository;


import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoginVerificationRepository  extends JpaRepository<LoginVerificationEntity, Integer>  {

    Boolean existsByCodeAndUuid(String code, String uuid);

    @Transactional
    @Modifying
    @Query("DELETE FROM LoginVerificationEntity rv WHERE rv.code = :code AND rv.uuid = :uuid")
    void deleteCodeAndUuid(String code, String uuid);

}
