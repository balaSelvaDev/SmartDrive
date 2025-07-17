package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BookingAddRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.UserIdNameDrivingLicenseResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingImpl booking;

    @PostMapping
    public ResponseEntity<BookingAddRequestDTO> createBooking(@RequestBody BookingAddRequestDTO dto) {
        BookingAddRequestDTO saved = booking.createBooking(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookingAddRequestDTO>> getAllBookings() {
        List<BookingAddRequestDTO> bookings = booking.getAllBookings();
        return ResponseEntity.ok(bookings);
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

}
