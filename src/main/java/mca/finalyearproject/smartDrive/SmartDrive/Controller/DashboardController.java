package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.ClientLocationResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.BookingDetailsResponse;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.GetCountsDetails;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private BookingImpl booking;

    @GetMapping("/counts")
    public GetCountsDetails getCountsDetails() {
        return booking.getCountsDetails();
    }

    @GetMapping("/booking-details")
    public List<BookingDetailsResponse> getBookingDetails() {
        return booking.getBookingDetails();
    }

}
