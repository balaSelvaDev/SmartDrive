package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.*;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleImageRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.ImageType;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    @Autowired
    private Cloudinary cloudinary;

    public PaginationResponse<VehicleResponseDTO> getAllVehicle(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<VehicleEntity> vehicleList = vehicleRepository.findAll(paging);
        List<VehicleResponseDTO> vehicleDtoList = vehicleList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        PaginationResponse<VehicleResponseDTO> response = new PaginationResponse<>(
                vehicleDtoList,
                vehicleList.getNumber(),
                vehicleList.getTotalPages(),
                vehicleList.getTotalElements()
        );
        return response;
    }

    public VehicleResponseDTO getVehicleById(Integer vehicleId) {
        return entityToDTO(vehicleRepository.findById(vehicleId).get());
    }

    public VehicleResponseDTO entityToDTO(VehicleEntity entity) {

        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setVehicleId(entity.getVehicleId());
        dto.setDescription(entity.getDescription());
        dto.setRegistrationNo(entity.getRegistrationNo());
        dto.setPricePerKm(entity.getPricePerKm());
        dto.setFuelType(entity.getFuelType());
        dto.setFuelCapacity(entity.getFuelCapacity());
        dto.setMileagePerLitre(entity.getMileagePerLitre());
        dto.setSeatingCapacity(entity.getSeatingCapacity());
        dto.setColor(entity.getColor());
        dto.setVehicleType(entity.getVehicleType());
        dto.setOwnerType(entity.getOwnerType());
        dto.setYearOfManufacture(entity.getYearOfManufacture());
        dto.setEngineCc(entity.getEngineCc());
        dto.setTorque(entity.getTorque());
        dto.setHorsepower(entity.getHorsepower());
        dto.setInsured(entity.getInsured());
        dto.setInsuranceExpiryDate(entity.getInsuranceExpiryDate());
        dto.setLastUpdatedInsuranceDate(entity.getLastUpdatedInsuranceDate());
        dto.setHasAirbags(entity.getHasAirbags());
        dto.setHasAbs(entity.getHasAbs());
        dto.setHasSunroof(entity.getHasSunroof());
        dto.setHasGps(entity.getHasGps());
        dto.setHasMusicSystem(entity.getHasMusicSystem());
        dto.setHasReverseCamera(entity.getHasReverseCamera());
        dto.setVehicleStatus(entity.getVehicleStatus());
        dto.setAvailable(entity.getAvailable());

        VehicleModelResponseDTO vehicleModelResponseDTO = VehicleModelServiceImpl.entityToDTO(entity.getModel());
        dto.setVehicleModelResponseDTO(vehicleModelResponseDTO);
        return dto;

    }

    @Transactional
    public VehicleEntity addVehicle(List<MultipartFile> images, VehicleAddRequestDTO dto) throws IOException {

        VehicleEntity entity = new VehicleEntity();

        // Set model reference
        VehicleModelEntity model = vehicleModelRepository.findById(dto.getModelId())
                .orElseThrow(() -> new RuntimeException("Model not found with ID: " + dto.getModelId()));
        entity.setModel(model);

        String vehicleName = model.getBrand().getBrandName() + " " + model.getModelName();
        entity.setVehicleName(vehicleName);
        entity.setDescription(dto.getDescription());
        entity.setRegistrationNo(dto.getRegistrationNo());
        entity.setPricePerKm(dto.getPricePerKm());
        entity.setFuelType(dto.getFuelType());
        entity.setFuelCapacity(dto.getFuelCapacity());
        entity.setMileagePerLitre(dto.getMileagePerLitre());
        entity.setSeatingCapacity(dto.getSeatingCapacity());
        entity.setColor(dto.getColor());
        entity.setVehicleType(dto.getVehicleType());
        entity.setOwnerType(dto.getOwnerType());
        entity.setYearOfManufacture(dto.getYearOfManufacture());
        entity.setEngineCc(dto.getEngineCc());
        entity.setTorque(dto.getTorque());
        entity.setHorsepower(dto.getHorsepower());
        entity.setInsured(dto.getInsured());
        entity.setInsuranceExpiryDate(dto.getInsuranceExpiryDate());
        entity.setLastUpdatedInsuranceDate(dto.getLastUpdatedInsuranceDate());
        entity.setHasAirbags(dto.getHasAirbags());
        entity.setHasAbs(dto.getHasAbs());
        entity.setHasSunroof(dto.getHasSunroof());
        entity.setHasGps(dto.getHasGps());
        entity.setHasMusicSystem(dto.getHasMusicSystem());
        entity.setHasReverseCamera(dto.getHasReverseCamera());

        // Default values
        entity.setAvailable(true);
        entity.setVehicleStatus(VehicleStatus.Active);
        VehicleEntity vehicleEntityResult = vehicleRepository.save(entity);

        // profile image uploaded code
        //
        String folder = "Smart-drive-booking-hub/Vehicle profile image";

        List<VehicleImageEntity> vehicelImageLists = new ArrayList<>();
        int count = 0;
        for (MultipartFile file : images) {
            MultipartFile image = file;
            count++;
            String publicId = "VEHICLE_PROFILE_" + vehicleEntityResult.getVehicleId() + "_" + count;
            vehicelImageLists.add(uploadImage(image, folder, publicId, vehicleEntityResult));
        }
        System.out.println("count:: " + count);
        vehicleImageRepository.saveAll(vehicelImageLists);

        return vehicleEntityResult;

    }

    private VehicleImageEntity uploadImage(MultipartFile profileImage, String folder, String publicId, VehicleEntity vehicleEntity) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap(
                "folder", folder,
                "public_id", publicId
        );
        String originalFilename = profileImage.getOriginalFilename();
        Map uploadResult = cloudinary.uploader().upload(profileImage.getBytes(), options);
        String uploadedUrl = (String) uploadResult.get("secure_url");
        VehicleImageEntity image = new VehicleImageEntity();
        image.setVehicleId(vehicleEntity.getVehicleId());
        image.setOriginalFileName(originalFilename);
        image.setAlternateFileName((String) uploadResult.get("public_id"));
        image.setImageUrl(uploadedUrl);
        image.setStatus(true);
        image.setImageType(ImageType.PROFILE_IMAGE);
        return image;
    }

    public List<BrandIdNameVMIdNameResponseDTO> getBrandIdNameVehicleIdName(String vehicleName, Integer limit) {
        if (vehicleName == null || vehicleName.trim().isEmpty()) {
            return vehicleRepository.findAll()
                    .stream().map(this::entityToBrandIdNameVMIdNameResponseDTO)
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return vehicleRepository.findByVehicleNameContainingIgnoreCase(vehicleName)
                .stream()
                .limit(limit)
                .map(this::entityToBrandIdNameVMIdNameResponseDTO)
                .collect(Collectors.toList());
    }

    public BrandIdNameVMIdNameResponseDTO entityToBrandIdNameVMIdNameResponseDTO(VehicleEntity entity) {
        BrandIdNameVMIdNameResponseDTO dto = new BrandIdNameVMIdNameResponseDTO();
        dto.setVehicleName(entity.getVehicleName());
        dto.setVehicleId(entity.getVehicleId());
        dto.setVehicleModelId(entity.getModel().getModelId());
        dto.setVehicleModelName(entity.getModel().getModelName());
        if(entity.getModel().getBrand() != null) {
            dto.setBrandId(entity.getModel().getBrand().getBrandId());
            dto.setBrandName(entity.getModel().getBrand().getBrandName());
        }
        return dto;
    }

}
