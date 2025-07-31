package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.*;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.AuthProvider;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.KycImageType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VerificationStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.*;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private UserKycDetailsRepository kycRepository;

    @Autowired
    private KycImageRepository kycImageRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public RegistrationVerificationDTO createUserByUser(UserCreateRequestDTO dto) {

        UserListEntity entity = new UserListEntity();
        entity.setAuthProvide(AuthProvider.LOCAL);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setFullName(utilityClass.createFullName(dto.getFirstName(), dto.getLastName()));
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
//        LocalDateTime lc = LocalDateTime.now().plusMinutes(2);
        verification.setVerifyStatus(VerificationStatus.CODE_GENERATED);
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(2));
        RegistrationVerificationEntity save = registrationVerificationRepository.save(verification);

        RegistrationVerificationDTO verificationDTO = new RegistrationVerificationDTO();
//        verificationDTO.setCode(save.getCode());
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
        if (b) {
            System.out.println("-----> 2 ");
            Boolean b1 = verificationRepository.existsByUuidAndCode(verificationDTO.getUuid(), verificationDTO.getCode());
            if (b1) {
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
                codeResponseDTO.setUserId(verificationDTO.getUserId());

                return codeResponseDTO;
            }
        }
        System.out.println("-----> 4 ");
        return codeResponseDTO;
    }

    @Transactional
    public boolean generatePassword(GeneratePasswordRequestDTO requestDTO) {

        Boolean b = verificationRepository.existsByUserIdAndUuid(requestDTO.getUserId(), requestDTO.getUuid());
        System.out.println("---<1>");
        if (b) {
            System.out.println("---<2>");

            LoginCredentialEntity loginCredentialEntity = new LoginCredentialEntity();
            UserListEntity byEmail = userRepository.findByEmail(requestDTO.getEmailId()).get();
            loginCredentialEntity.setUser(byEmail);
            loginCredentialEntity.setPassword(bCryptPasswordEncoder.encode(requestDTO.getPassword()));
            loginCredentialEntity.setLastLoginTime(LocalDateTime.now());
            loginCredentialEntity.setEnabled(true);
            Role role = roleRepository.findById(Long.valueOf(2))
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            loginCredentialEntity.setRole(role);
            loginCredentialRepository.save(loginCredentialEntity);
            System.out.println("---<3>");
            verificationRepository.deleteByIdAndUuid(requestDTO.getUserId(), requestDTO.getUuid());
            return true;
        }
        return false;

    }

    public RegistrationVerificationDTO resetVerificationCode(Integer userId, String emailId) {
        UserListEntity userEntity = userRepository.findByUserIdAndEmail(userId, emailId).orElseThrow(() -> new RuntimeException("User not found"));
        RegistrationVerificationDTO verificationDTO = new RegistrationVerificationDTO();
        if (userEntity != null) {
            String verificationCode = utilityClass.createRandomCode();
            String UUID = utilityClass.createUuidCode();

            RegistrationVerificationEntity verification = new RegistrationVerificationEntity();
            verification.setUserId(userEntity.getUserId());
            verification.setUuid(UUID);
            verification.setCode(Integer.valueOf(verificationCode));
            verification.setCreatedTime(LocalDateTime.now());
//            LocalDateTime lc = LocalDateTime.now().plusMinutes(10);
            verification.setVerifyStatus(VerificationStatus.CODE_GENERATED);
            verification.setExpiryTime(LocalDateTime.now().plusMinutes(2));
            RegistrationVerificationEntity save = registrationVerificationRepository.save(verification);


//            verificationDTO.setCode(save.getCode());
            verificationDTO.setUuid(save.getUuid());
            verificationDTO.setUserId(save.getUserId());
            verificationDTO.setEmailId(userEntity.getEmail());

            try {
                emailService.sendVerificationEmail(userEntity.getEmail(), verificationCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return verificationDTO;
        }
        return verificationDTO;
    }


    @Transactional
    public UserKycDetailsEntity createUserByAdmin(UserCreateByAdminRequestDTO dto, MultipartFile profileImage, MultipartFile drivingLicenseImage, List<MultipartFile> idProofFiles) throws IOException {

        UserListEntity entity = new UserListEntity();
        entity.setAuthProvide(AuthProvider.LOCAL);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setFullName(utilityClass.createFullName(dto.getFirstName(), dto.getLastName()));
        entity.setIsActive(1);

        UserListEntity userListEntity = userRepository.save(entity);

        UserKycDetailsEntity kycDetailsEntity = new UserKycDetailsEntity();
        kycDetailsEntity.setUser(userListEntity);
        kycDetailsEntity.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());
        kycDetailsEntity.setIdProofType(dto.getIdProofType());
        kycDetailsEntity.setIdProofNumber(dto.getIdProofNumber());

        kycDetailsEntity.setAddressLine1(dto.getAddressLine1());
        kycDetailsEntity.setAddressLine2(dto.getAddressLine2());
        kycDetailsEntity.setDistrict(dto.getDistrict());
        kycDetailsEntity.setTaluk(dto.getTaluk());
        kycDetailsEntity.setState(dto.getState());
        kycDetailsEntity.setPincode(dto.getPincode());
        kycDetailsEntity.setCountry(dto.getCountry());

        kycDetailsEntity.setFatherName(dto.getFatherName());
        kycDetailsEntity.setMotherName(dto.getMotherName());

        kycDetailsEntity.setNomineeName(dto.getNomineeName());
        kycDetailsEntity.setNomineeRelation(dto.getNomineeRelation());
        kycDetailsEntity.setNomineePhone(dto.getNomineePhone());

        kycDetailsEntity.setOccupation(dto.getOccupation());
        kycDetailsEntity.setCompanyName(dto.getCompanyName());
        kycDetailsEntity.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());
        UserKycDetailsEntity userKycDetailsEntity = kycRepository.save(kycDetailsEntity);

        System.out.println("User created...");

        List<KycImageEntity> kycImageEntities = new ArrayList<>();
        // user profile
        String userProfileImagePath = "Smart-drive-booking-hub/User profile image";
        String userProfileImageId = "USER_PROFILE_" + userListEntity.getUserId() + "_" + userKycDetailsEntity.getKycId();
        KycImageEntity userProfileEntity = uploadImage(profileImage, userProfileImagePath, userProfileImageId, userKycDetailsEntity, KycImageType.PROFILE);

        // driving license
        String drivingLicenseImagePath = "Smart-drive-booking-hub/Driving license";
        String drivingLicenseImageId = "USER_LICENSE_" + userListEntity.getUserId() + "_" + userKycDetailsEntity.getKycId();
        KycImageEntity drivingLicenseEntity = uploadImage(drivingLicenseImage, drivingLicenseImagePath, drivingLicenseImageId, userKycDetailsEntity, KycImageType.DRIVING_LICENSE);

        kycImageEntities.add(userProfileEntity);
        kycImageEntities.add(drivingLicenseEntity);

        //
        String folder1 = "";
        String publicId = "";
        KycImageType imageType;
        for (MultipartFile file : idProofFiles) {
            MultipartFile file1 = file;
            switch (dto.getIdProofType().name()) {
                case "PAN_CARD":
                    folder1 = "Smart-drive-booking-hub/Pan card";
                    publicId = "USER_PANCARD_" + userListEntity.getUserId() + "_" + userKycDetailsEntity.getKycId();
                    imageType = KycImageType.PAN_CARD;
                    break;
                case "AADHAAR":
                    folder1 = "Smart-drive-booking-hub/Aadhar card";
                    publicId = "USER_AADHAR_" + userListEntity.getUserId() + "_" + userKycDetailsEntity.getKycId();
                    imageType = KycImageType.AADHAAR;
                    break;
                case "PASSPORT":
                    folder1 = "Smart-drive-booking-hub/Driving license";
                    publicId = "USER_LICENSE_" + userListEntity.getUserId() + "_" + userKycDetailsEntity.getKycId();
                    imageType = KycImageType.PASSPORT;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid document type: " + userKycDetailsEntity.getKycId());
            }
            KycImageEntity idProofTypeEntity = uploadImage(file1, folder1, publicId, userKycDetailsEntity, imageType);
        }

        kycImageRepository.saveAll(kycImageEntities);
        return userKycDetailsEntity;

    }

    private KycImageEntity uploadImage(MultipartFile profileImage, String folder, String publicId, UserKycDetailsEntity userKycDetailsEntity, KycImageType imageType) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap("folder", folder, "public_id", publicId);
        String originalFilename = profileImage.getOriginalFilename();
        Map uploadResult = cloudinary.uploader().upload(profileImage.getBytes(), options);
        String uploadedUrl = (String) uploadResult.get("secure_url");
        KycImageEntity image = new KycImageEntity();
        image.setKyc(userKycDetailsEntity);
        image.setOriginalFileName(originalFilename);
        image.setAlternateFileName((String) uploadResult.get("public_id"));
        image.setImageUrl(uploadedUrl);
        image.setImageType(imageType);
        image.setStatus(true);
        return image;
    }

    public PaginationResponse<UserAndKycResponseDTO> getUserAndKycDetails(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<UserListEntity> userAndKycEntity = userRepository.findAll(paging);
        List<UserAndKycResponseDTO> userAndKycDTO = userAndKycEntity.stream().map(this::entityToUserAndKycResponseDTO).collect(Collectors.toList());
        PaginationResponse<UserAndKycResponseDTO> response = new PaginationResponse<>(
                userAndKycDTO,
                userAndKycEntity.getNumber(),
                userAndKycEntity.getTotalPages(),
                userAndKycEntity.getTotalElements()
        );
        return response;
    }

    public UserAndKycResponseDTO getUserAndKycDetailsById(Integer userId) {

        Optional<UserListEntity> byId = userRepository.findById(userId);
        return entityToUserAndKycResponseDTO(byId.get());

    }

    public UserAndKycResponseDTO entityToUserAndKycResponseDTO(UserListEntity entity) {
        if (entity == null) return null;
        UserAndKycResponseDTO userAndKycResponseDTO = new UserAndKycResponseDTO();

        UserDetailsResponseDTO dto = new UserDetailsResponseDTO();
        dto.setUserId(entity.getUserId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setIsActive(entity.getIsActive());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setFullName(entity.getFullName());
        userAndKycResponseDTO.setUserDetailsResponseDTO(dto);

        UserKycDetailsEntity userKycEntity = entity.getUser();
        if (userKycEntity != null) {
            UserKycDetailsResponseDTO dto1 = new UserKycDetailsResponseDTO();
            dto1.setKycId(userKycEntity.getKycId());
            dto1.setDrivingLicenseNumber(userKycEntity.getDrivingLicenseNumber());
            dto1.setIdProofType(userKycEntity.getIdProofType());
            dto1.setIdProofNumber(userKycEntity.getIdProofNumber());
            dto1.setAddressLine1(userKycEntity.getAddressLine1());
            dto1.setAddressLine2(userKycEntity.getAddressLine2());
            dto1.setTaluk(userKycEntity.getTaluk());
            dto1.setDistrict(userKycEntity.getDistrict());
            dto1.setState(userKycEntity.getState());
            dto1.setPincode(userKycEntity.getPincode());
            dto1.setCountry(userKycEntity.getCountry());
            dto1.setFatherName(userKycEntity.getFatherName());
            dto1.setMotherName(userKycEntity.getMotherName());
            dto1.setNomineeName(userKycEntity.getNomineeName());
            dto1.setNomineeRelation(userKycEntity.getNomineeRelation());
            dto1.setNomineePhone(userKycEntity.getNomineePhone());
            dto1.setOccupation(userKycEntity.getOccupation());
            dto1.setCompanyName(userKycEntity.getCompanyName());
            dto1.setAlternatePhoneNumber(userKycEntity.getAlternatePhoneNumber());
            userAndKycResponseDTO.setUserKycDetailsResponseDTO(dto1);

            List<KycImageEntity> kycImage = userKycEntity.getKycImage();
            if (kycImage != null) {
                Map<KycImageType, List<UserKycImageResponseDTO>> collect = kycImage
                        .stream()
                        .map(this::kycImageEntityToKycImageDTO)
                        .collect(Collectors.groupingBy(UserKycImageResponseDTO::getImageType));
                userAndKycResponseDTO.setUserKycImageResponseDTO(collect);
            }
        }

        return userAndKycResponseDTO;
    }

    public UserKycImageResponseDTO kycImageEntityToKycImageDTO(KycImageEntity entity) {
        UserKycImageResponseDTO dto = new UserKycImageResponseDTO();
        dto.setImageUrl(entity.getImageUrl());
        dto.setOriginalFileName(entity.getOriginalFileName());
        dto.setAlternateFileName(entity.getAlternateFileName());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());
        dto.setImageType(entity.getImageType());
        return dto;
    }


}
