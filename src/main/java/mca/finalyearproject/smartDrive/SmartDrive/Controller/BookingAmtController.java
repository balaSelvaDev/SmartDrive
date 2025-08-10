package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BookingAmtRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BookingAmtEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BookingAmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking-amt")
public class BookingAmtController {

    @Autowired
    private BookingAmtService bookingAmtService;

    @PostMapping
    public BookingAmtEntity createBooking(@RequestBody BookingAmtRequestDTO dto) {
        return bookingAmtService.saveBookingAmtDetail(dto);
    }

}
