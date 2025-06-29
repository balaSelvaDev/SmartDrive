package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.RegistrationVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCredentialRepository  extends JpaRepository<LoginCredentialEntity, Integer> {

    @Query("SELECT lc FROM LoginCredentialEntity lc " +
            "JOIN lc.user u " +
            "WHERE u.email = :emailId AND lc.password = :password")
    LoginCredentialEntity findByEmailAndPassword(String emailId, String password);


}
