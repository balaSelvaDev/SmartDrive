package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleModelResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.VehicleResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BrandEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleModelEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleModelRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

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

    public VehicleEntity addVehicle(VehicleAddRequestDTO dto) {

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

        return vehicleRepository.save(entity);

    }





}
