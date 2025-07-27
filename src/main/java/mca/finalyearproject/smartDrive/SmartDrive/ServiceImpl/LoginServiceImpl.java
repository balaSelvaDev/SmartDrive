package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.LoginRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.LoginResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.LoginVerificationCodeRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.ResetDetailsResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginCredentialEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.LoginVerificationEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginCredentialRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.LoginVerificationRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class LoginServiceImpl {

    @Autowired
    LoginCredentialRepository loginCredentialRepository;

    @Autowired
    LoginVerificationRepository verificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UtilityClass utilityClass;

    @Autowired
    EmailService emailService;

    @Transactional
    public LoginResponseDTO loginCheck(LoginRequestDTO requestDTO) {
        LoginCredentialEntity byEmailAndPassword = loginCredentialRepository.findByEmailAndPassword(requestDTO.getEmailId(), requestDTO.getPassword());
        System.out.println("---<1>");
        if (byEmailAndPassword != null) {
            System.out.println("---<2>");
            String verificationCode = utilityClass.createRandomCode();
            String UUID = utilityClass.createUuidCode();
            LoginVerificationEntity verificationEntity = new LoginVerificationEntity();
            verificationEntity.setCode(verificationCode);
            verificationEntity.setUuid(UUID);
            verificationEntity.setCreatedTime(LocalDateTime.now());
            verificationEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10));
            UserListEntity byEmail = userRepository.findByEmail(requestDTO.getEmailId()).get();
            verificationEntity.setUser(byEmail);
            LoginVerificationEntity save = verificationRepository.save(verificationEntity);
            try {
                emailService.sendVerificationEmail(requestDTO.getEmailId(), verificationCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setEmailId(byEmail.getEmail());
            loginResponseDTO.setUuid(UUID);
            loginResponseDTO.setUserId(save.getUser().getUserId());
            return loginResponseDTO;
        }
        return null;
    }

    @Transactional
    public Boolean checkVerificationCode1(LoginVerificationCodeRequestDTO requestDTO) {

        Boolean b = verificationRepository.existsByCodeAndUuid(requestDTO.getCode(), requestDTO.getUuid());
        if (b) {
            verificationRepository.deleteCodeAndUuid(requestDTO.getCode(), requestDTO.getUuid());
            return true;
        }
        return false;

    }

    @Transactional
    public LoginResponseDTO resetVerificationCode(ResetDetailsResponseDTO requestDTO) {

        UserListEntity userEntity = userRepository.findByEmail(requestDTO.getEmailId()).get();
        LoginVerificationEntity loginVerificationEntity = verificationRepository.findByUuid(requestDTO.getUuid());
        if (userEntity != null & loginVerificationEntity != null) {
            String verificationCode = utilityClass.createRandomCode();
            String UUID = utilityClass.createUuidCode();
            loginVerificationEntity.setUuid(UUID);
            loginVerificationEntity.setCode(verificationCode);
            loginVerificationEntity.setCreatedTime(LocalDateTime.now());
            loginVerificationEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10));

            try {
                emailService.sendVerificationEmail(requestDTO.getEmailId(), verificationCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setEmailId(requestDTO.getEmailId());
            loginResponseDTO.setUuid(UUID);
            loginResponseDTO.setUserId(userEntity.getUserId());
            return loginResponseDTO;
        }

        return null;
    }

//    public  User loginRegisterByGoogleOAuth2(OAuth2AuthenticationToken auth2AuthenticationToken){
//
//        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
//        String email = oAuth2User.getAttribute("email");
//        String name = oAuth2User.getAttribute("name");
//
//        log.info("USER Email FROM GOOGLE  IS {}",email );
//        log.info("USER Name from GOOGLE IS {}",name );
//
//        User user = usersRepository.findByEmail(email).orElse(null);
//        if (user == null) {
//            user = new User();
//            user.setName(name);
//            user.setEmail(email);
//            user.setAuthProvide(AuthProvider.GOOGLE);
//            return usersRepository.save(user);
//        }
//        return user;
//    }
}
