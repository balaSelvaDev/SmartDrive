package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.AuthProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserListEntity, Integer> {

    Optional<UserListEntity> findByEmail(String email);

    List<UserListEntity> findByFullNameContainingIgnoreCase(String userName);

    Optional<UserListEntity> findByUserIdAndEmail(Integer userId, String emailId);

    Page<UserListEntity> findByauthProvideNot(AuthProvider authProvider, Pageable page);
}
