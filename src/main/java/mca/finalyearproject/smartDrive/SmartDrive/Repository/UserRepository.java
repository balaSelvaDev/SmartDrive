package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserListEntity, Integer> {

    UserListEntity findByEmail(String email);

    List<UserListEntity> findByFullNameContainingIgnoreCase(String userName);

    Optional<UserListEntity> findByUserIdAndEmail(Integer userId, String emailId);
}
