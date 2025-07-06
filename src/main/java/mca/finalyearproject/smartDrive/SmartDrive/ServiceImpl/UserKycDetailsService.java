package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserKycDetailsAddDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserKycDetailsEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserKycDetailsRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserKycDetailsService {

    @Autowired
    private UserKycDetailsRepository kycRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveKycDetails(UserKycDetailsAddDTO dto) {
        UserKycDetailsEntity entity = new UserKycDetailsEntity();

        entity.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        entity.setIdProofType(dto.getIdProofType());
        entity.setIdProofNumber(dto.getIdProofNumber());

        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPincode(dto.getPincode());
        entity.setCountry(dto.getCountry());

        entity.setFatherName(dto.getFatherName());
        entity.setMotherName(dto.getMotherName());

        entity.setNomineeName(dto.getNomineeName());
        entity.setNomineeRelation(dto.getNomineeRelation());
        entity.setNomineePhone(dto.getNomineePhone());

        entity.setOccupation(dto.getOccupation());
        entity.setCompanyName(dto.getCompanyName());
        entity.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());

        // Set user reference
        UserListEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(user);
        kycRepository.save(entity);
    }

}
