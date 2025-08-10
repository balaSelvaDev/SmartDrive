package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BookingAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.KyImageResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserIdNameDrivingLicenseResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.*;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.BookingStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BookingRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.VehicleRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Util.GlobalStatusType;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingImpl {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public BookingAddRequestDTO createBooking(BookingAddRequestDTO dto) {
        BookingEntity entity = new BookingEntity();

        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPickupLocation(dto.getPickupLocation());
        entity.setDropLocation(dto.getDropLocation());
        entity.setBookingStatus(BookingStatus.AVAILABLE);
        entity.setBookingType(dto.getBookingType());
        entity.setReturnDateTime(dto.getReturnDateTime());

//        entity.setPaymentMode(dto.getPaymentMode());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setPaymentReference(dto.getPaymentReference());

        entity.setTotalAmount(dto.getTotalAmount());
        entity.setDiscountAmount(dto.getDiscountAmount());

        entity.setDistanceKm(dto.getDistanceKm());
        entity.setTripAmt(dto.getTripAmt());
        entity.setConvenienceAmt(dto.getConvenienceAmt());
        entity.setRefundableAmt(dto.getRefundableAmt());
        entity.setFinalAmount(dto.getFinalAmount());

        // Optional fields for cancellation
        entity.setCancellationReason(dto.getCancellationReason());
        entity.setCancelledBy(dto.getCancelledBy());
        entity.setCancellationDate(dto.getCancellationDate());

        // Generate Unique Booking Reference
        String bookingRef = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        while (bookingRepository.existsByBookingReference(bookingRef)) {
            bookingRef = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        }
        entity.setBookingReference(bookingRef);

        // Set Relations
        UserListEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(user);

        VehicleEntity vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        entity.setVehicle(vehicle);

        // Save and return DTO
        BookingEntity saved = bookingRepository.save(entity);
//        dto.setBookingId(saved.getBookingId());
        dto.setBookingReference(saved.getBookingReference());
        return dto;
    }


    public PaginationResponse<BookingAddRequestDTO> getAllBookings(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<BookingEntity> all = bookingRepository.findAll(paging);
        List<BookingAddRequestDTO> brandDtoList = all.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        PaginationResponse<BookingAddRequestDTO> response = new PaginationResponse<>(
                brandDtoList,
                all.getNumber(),
                all.getTotalPages(),
                all.getTotalElements()
        );
        return response;
    }

    public BookingAddRequestDTO getBookingById(Integer id) {
        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        return mapToDTO(entity);
    }

    private BookingAddRequestDTO mapToDTO(BookingEntity entity) {
        BookingAddRequestDTO dto = new BookingAddRequestDTO();

        dto.setBookingId(entity.getBookingId());
        dto.setBookingReference(entity.getBookingReference());
        dto.setUserId(entity.getUser().getUserId());
        dto.setVehicleId(entity.getVehicle().getVehicleId());

        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setPickupLocation(entity.getPickupLocation());
        dto.setDropLocation(entity.getDropLocation());
        dto.setBookingStatus(entity.getBookingStatus());

        dto.setBookingType(entity.getBookingType());
        dto.setReturnDateTime(entity.getReturnDateTime());

//        dto.setPaymentMode(entity.getPaymentMode());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setPaymentReference(entity.getPaymentReference());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setDiscountAmount(entity.getDiscountAmount());

        dto.setDistanceKm(entity.getDistanceKm());
        dto.setTripAmt(entity.getTripAmt());
        dto.setConvenienceAmt(entity.getConvenienceAmt());
        dto.setRefundableAmt(entity.getRefundableAmt());
        dto.setFinalAmount(entity.getFinalAmount());

        dto.setCancellationReason(entity.getCancellationReason());
        dto.setCancelledBy(entity.getCancelledBy());
        dto.setCancellationDate(entity.getCancellationDate());

        return dto;
    }

    public List<UserIdNameDrivingLicenseResponseDTO> getUserIdNameDL(String userName, int limit) {
        if (userName == null || userName.trim().isEmpty()) {
            return userRepository.findAll()
                    .stream().map(this::entityToUserIdNameDLResponseDTO)
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return userRepository.findByFullNameContainingIgnoreCase(userName)
                .stream()
                .limit(limit)
                .map(this::entityToUserIdNameDLResponseDTO)
                .collect(Collectors.toList());
    }

    public UserIdNameDrivingLicenseResponseDTO entityToUserIdNameDLResponseDTO(UserListEntity entity) {
        UserIdNameDrivingLicenseResponseDTO dto = new UserIdNameDrivingLicenseResponseDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getFullName());
        if (entity.getUser() != null) {
            dto.setDrivingLicense(entity.getUser().getDrivingLicenseNumber());
            if (entity.getUser().getKycImage() != null) {
                List<KyImageResponseDTO> kytImgDto = entity.getUser().getKycImage().stream().map(this::entityToKyImageResponseDTO).collect(Collectors.toList());
                dto.setKycImageEntityList(kytImgDto);
            }
        }
        return dto;
    }

    public KyImageResponseDTO entityToKyImageResponseDTO(KycImageEntity entity) {
        KyImageResponseDTO dto = new KyImageResponseDTO();
        dto.setImageUrl(entity.getImageUrl());
        dto.setAlternateFileName(entity.getAlternateFileName());
        dto.setOriginalFileName(entity.getOriginalFileName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}
