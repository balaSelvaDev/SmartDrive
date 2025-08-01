package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.*;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.*;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.BookingStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleStatus;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.VehicleAvailabilityByVehicleId;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.ClientLocationRepository;
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
import java.time.LocalDateTime;
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

    @Autowired
    private ClientLocationRepository clientLocationRepository;

    public PaginationResponse<VehicleAvailabilityDTO> getAllVehicleForCustomer(int page, int size,
                                                                               LocalDateTime userPickupDatetime,
                                                                               LocalDateTime userDropDatetime,
                                                                               Boolean isVisibleOnline, String vehicleStatus) {
        Pageable paging = PageRequest.of(page, size);
        Page<Object[]> vehicleList =
                vehicleRepository.findVehiclesWithDetailedAvailability(userPickupDatetime, userDropDatetime, isVisibleOnline, vehicleStatus, paging);
        List<VehicleAvailabilityDTO> collect = vehicleList.getContent().stream().map(this::entityToAllVehicleForCustomer).collect(Collectors.toList());
        return new PaginationResponse<>(
                collect,
                vehicleList.getNumber(),
                vehicleList.getTotalPages(),
                vehicleList.getTotalElements()
        );
    }

    public VehicleAvailabilityDTO entityToAllVehicleForCustomer(Object obj[]) {

        VehicleAvailabilityDTO dto = new VehicleAvailabilityDTO();
        dto.setAvailabilityStatus(String.valueOf(obj[0]));
        dto.setBookingStatus(String.valueOf(obj[1]));
        dto.setVehicleId(Integer.parseInt(obj[2].toString()));

        dto.setVehicleName(String.valueOf(obj[3]));
        dto.setPricePerKm(String.valueOf(obj[5]));
        dto.setMileagePerLitre(String.valueOf(obj[6]));
        dto.setVehicleType(String.valueOf(obj[7]));
        dto.setFuelType(String.valueOf(obj[8]));
        dto.setSeatingCapacity(Integer.valueOf(obj[9].toString()));

        List<VehicleImageEntity> vehicleImageList = vehicleImageRepository.findByVehicleId(dto.getVehicleId());
//        List<VehicleImageEntity> vehicleImageList = entity.getVehicleImageList();
        if (vehicleImageList != null) {
            dto.setVehicleImageList(vehicleImageList.stream().map(this::entityToVehicleImageResponseDTO).collect(Collectors.toList()));
        }
        return dto;

    }

    public PaginationResponse<VehicleResponseDTO> getAllVehicle(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<VehicleEntity> vehicleList = vehicleRepository.findAll(paging);
        List<VehicleResponseDTO> vehicleDtoList = vehicleList.stream()
                .map(this::entityToDTO)
                .filter(a -> a.getVisibleOnline() == true)
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
        dto.setTransportType(entity.getTransportType());
        dto.setBookingStatus(entity.getBookingStatus());
        dto.setVehicleName(entity.getVehicleName());
        dto.setDescription(entity.getDescription());
        dto.setRegistrationNo(entity.getRegistrationNo());
        dto.setPricePerKm(entity.getPricePerKm());
        dto.setConvenienceFee(entity.getConvenienceFee());
        dto.setRefundableAmt(entity.getRefundableAmt());
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
        dto.setVisibleOnline(entity.getVisibleOnline());


        ClientLocationEntity clientLocation = entity.getClientLocation();
        if (clientLocation != null && clientLocation.getActive()) {
            dto.setClientLocation(entityToClientLocationResponseDTO(clientLocation));
        }

        List<VehicleImageEntity> vehicleImageList = entity.getVehicleImageList();
        if (vehicleImageList != null) {
            List<VehicleImageResponseDTO> collect = vehicleImageList.stream().filter(list -> list.getStatus() == true).map(this::entityToVehicleImageResponseDTO).collect(Collectors.toList());
            dto.setVehicleImagesList(collect);
        }

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
        entity.setBookingStatus(BookingStatus.AVAILABLE);
        entity.setTransportType(dto.getTransportType());
        entity.setVehicleName(vehicleName);
        entity.setDescription(dto.getDescription());
        entity.setRegistrationNo(dto.getRegistrationNo());
        entity.setPricePerKm(dto.getPricePerKm());
        entity.setConvenienceFee(dto.getConvenienceFee());
        entity.setRefundableAmt(dto.getRefundableAmt());
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
        entity.setVisibleOnline(dto.getVisibleOnline());

        ClientLocationEntity clientLocationEntity = clientLocationRepository.findById(dto.getClientLocationId())
                .orElseThrow(() -> new RuntimeException("client location not found with ID: " + dto.getClientLocationId()));
        entity.setClientLocation(clientLocationEntity);

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
        if (entity.getModel().getBrand() != null) {
            dto.setBrandId(entity.getModel().getBrand().getBrandId());
            dto.setBrandName(entity.getModel().getBrand().getBrandName());
        }
        dto.setPricePerKm(entity.getPricePerKm());
        dto.setConvenienceFee(entity.getConvenienceFee());
        dto.setRefundableAmt(entity.getRefundableAmt());
        return dto;
    }

    public ClientLocationResponseDTO entityToClientLocationResponseDTO(ClientLocationEntity entity) {
        ClientLocationResponseDTO dto = new ClientLocationResponseDTO();
        dto.setActive(entity.getActive());
        dto.setClientLocationId(entity.getClientLocationId());
        dto.setPincode(entity.getPincode());
        dto.setGoogleMapUrl(entity.getGoogleMapUrl());
        dto.setAddress(entity.getAddress());
        dto.setLocationName(entity.getLocationName());
        dto.setDisplayName(entity.getDisplayName());
        return dto;
    }

    public VehicleImageResponseDTO entityToVehicleImageResponseDTO(VehicleImageEntity entity) {
        VehicleImageResponseDTO dto = new VehicleImageResponseDTO();
        dto.setImageId(entity.getImageId());
        dto.setImageType(entity.getImageType());
        dto.setImageUrl(entity.getImageUrl());
        dto.setOriginalFileName(entity.getOriginalFileName());
        dto.setAlternateFileName(entity.getAlternateFileName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public VehicleAvailabilityByVehicleId getVehicleAvailabilityByVehicleIdAndDateTime(LocalDateTime userPickupDatetime,
                                                                                       LocalDateTime userDropDatetime,
                                                                                       Integer vehicleId) {
        return vehicleRepository.getVehicleAvailabilityByVehicleIdAndDateTime(userPickupDatetime, userDropDatetime, "Active", vehicleId);
    }


}
