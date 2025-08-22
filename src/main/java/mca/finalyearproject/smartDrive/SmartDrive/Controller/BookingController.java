package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BookingAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameVMIdNameResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserIdNameDrivingLicenseResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BookingImpl;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.VehicleServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingImpl booking;

    @Autowired
    private VehicleServiceImpl vehicleService;

    @Autowired
    private UtilityClass utilityClass;

    @PostMapping
    public ResponseEntity<BookingAddRequestDTO> createBooking(@RequestBody BookingAddRequestDTO dto, HttpServletRequest request) {
        BookingAddRequestDTO saved = booking.createBooking(dto);
        System.out.println("-------------------------");
        System.out.println("websocket notification...");
        String authHeader = request.getHeader("Authorization");
        utilityClass.sendNotificationToAdmin(authHeader, saved.getBookingId());
        System.out.println("edit users......");
        System.out.println("-------------------------");
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<BookingAddRequestDTO>> getAllBookings(@RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(booking.getAllBookings(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingAddRequestDTO> getBookingById(@PathVariable Integer id) {
        BookingAddRequestDTO dto = booking.getBookingById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search/username")
    public ResponseEntity<List<UserIdNameDrivingLicenseResponseDTO>> getUserIdNameDL(@RequestParam(required = false, value = "userName") String userName,
                                                                                     @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(booking.getUserIdNameDL(userName, limit));
    }

    @GetMapping("/search/vehiclename")
    public ResponseEntity<List<BrandIdNameVMIdNameResponseDTO>> getVehicleById(@RequestParam(required = false, value = "vehicleName") String vehicleName,
                                                                               @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(vehicleService.getBrandIdNameVehicleIdName(vehicleName, limit));
    }


}
