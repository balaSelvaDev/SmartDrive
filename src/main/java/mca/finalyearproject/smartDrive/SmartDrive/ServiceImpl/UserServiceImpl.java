package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.RegistrationVerificationEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VerificationStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginCredentialRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.RegistrationVerificationRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationVerificationRepository verificationRepository;


    @Autowired
    RegistrationVerificationRepository registrationVerificationRepository;

    @Autowired
    LoginCredentialRepository loginCredentialRepository;

    @Autowired
    UtilityClass utilityClass;

    @Transactional
    public RegistrationVerificationDTO createUser(UserCreateRequestDTO dto) {

        UserListEntity entity = new UserListEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setIsActive(1);

        UserListEntity userListEntity = userRepository.save(entity);
        System.out.println("User created...");
        String verificationCode = utilityClass.createRandomCode();
        String UUID = utilityClass.createUuidCode();
        RegistrationVerificationEntity verification = new RegistrationVerificationEntity();
        verification.setUserId(userListEntity.getUserId());
        verification.setUuid(UUID);
        verification.setCode(Integer.valueOf(verificationCode));
        verification.setCreatedTime(LocalDateTime.now());
        LocalDateTime lc = LocalDateTime.now().plusMinutes(10);
        verification.setVerifyStatus(VerificationStatus.CODE_GENERATED);
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(10));
        RegistrationVerificationEntity save = registrationVerificationRepository.save(verification);

        RegistrationVerificationDTO verificationDTO = new RegistrationVerificationDTO();
        verificationDTO.setCode(save.getCode());
        verificationDTO.setUuid(save.getUuid());
        verificationDTO.setUserId(save.getUserId());
        verificationDTO.setEmailId(dto.getEmail());

        try {
            emailService.sendVerificationEmail(dto.getEmail(), verificationCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return verificationDTO;

    }

    @Transactional
    public CheckVerificationCodeResponseDTO checkVerificationCode(VerificationCodeRequestDTO verificationDTO) {

        Boolean b = verificationRepository.existsValidByUuidAndCode(LocalDateTime.now());
        CheckVerificationCodeResponseDTO codeResponseDTO = new CheckVerificationCodeResponseDTO();
        System.out.println("-----> 1 ");
        if(b) {
            System.out.println("-----> 2 ");
            Boolean b1 = verificationRepository.existsByUuidAndCode(verificationDTO.getUuid(), verificationDTO.getCode());
            if(b1) {
                System.out.println("-----> 3 ");
                // verificationRepository.deleteByUuidAndCode(verificationDTO.getUuid(), verificationDTO.getCode());
                RegistrationVerificationEntity byUuidAndCode = verificationRepository.findByUuidAndCode(verificationDTO.getUuid(), verificationDTO.getCode());
                String UUID = java.util.UUID.randomUUID().toString();
                byUuidAndCode.setUuid(UUID);
                byUuidAndCode.setCode(null);
                byUuidAndCode.setVerifyStatus(VerificationStatus.CODE_SUCCESS);
                verificationRepository.save(byUuidAndCode);


                codeResponseDTO.setUuid(UUID);
                codeResponseDTO.setEmailId(verificationDTO.getEmailId());
                codeResponseDTO.setUserId(verificationDTO.getCode());

                return codeResponseDTO;
            }
        }
        System.out.println("-----> 4 ");
        return codeResponseDTO;
    }


    @Transactional
    public void generatePassword(GeneratePasswordRequestDTO requestDTO) {

        Boolean b = verificationRepository.existsByUserIdAndUuid(requestDTO.getUserId(), requestDTO.getUuid());
        System.out.println("---<1>");
        if(b) {
            System.out.println("---<2>");

            LoginCredentialEntity loginCredentialEntity = new LoginCredentialEntity();
            UserListEntity byEmail = userRepository.findByEmail(requestDTO.getEmailId());
            loginCredentialEntity.setUser(byEmail);
            loginCredentialEntity.setPassword(requestDTO.getPassword());
            loginCredentialEntity.setLastLoginTime(LocalDateTime.now());
            loginCredentialRepository.save(loginCredentialEntity);
            System.out.println("---<3>");
            verificationRepository.deleteByIdAndUuid(requestDTO.getUserId(), requestDTO.getUuid());
        }

    }
}
