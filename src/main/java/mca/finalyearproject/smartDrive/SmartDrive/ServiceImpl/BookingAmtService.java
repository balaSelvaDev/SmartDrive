package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.BookingAmtRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BookingAmtEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.BookingEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.UserListEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.PaymentStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BookingAmtRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.BookingRepository;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BookingAmtService {

    @Autowired
    private BookingAmtRepository bookingAmtRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BookingAmtEntity saveBookingAmtDetail(BookingAmtRequestDTO dto) {

        BookingAmtEntity entity = new BookingAmtEntity();

        BookingEntity bookingDetails = bookingRepository.findById(dto.getBookingId()).orElseThrow(() -> new RuntimeException("Booking detail not found"));
        System.out.println("bookingDetails.getPendingAmt().equals(BigDecimal.ZERO): " + bookingDetails.getPendingAmt().equals(BigDecimal.ZERO));
        System.out.println("bookingDetails.getTotalAmount().equals(bookingDetails.getPaidAmt()): " + bookingDetails.getTotalAmount().equals(bookingDetails.getPaidAmt()));
        System.out.println("bookingDetails.getPendingAmt(): " + bookingDetails.getPendingAmt());
        System.out.println("BigDecimal.ZERO: " + BigDecimal.ZERO);
        BigDecimal pendingAmt1 = new BigDecimal(bookingDetails.getPendingAmt().toString());
        System.out.println(pendingAmt1);
        if (bookingDetails.getPendingAmt().compareTo(BigDecimal.ZERO) == 0 && bookingDetails.getTotalAmount().equals(bookingDetails.getPaidAmt())) {
            throw new RuntimeException("Paid full amount successfully");
        }

        //
        BigDecimal totalAmt = new BigDecimal(bookingDetails.getTotalAmount().toString());
        BigDecimal userPaidAmt = new BigDecimal(dto.getPaidAmount().toString());
        System.out.println("bookingDetails.getTotalAmount().compareTo(dto.getPaidAmount()): " + totalAmt.compareTo(userPaidAmt));
        if (userPaidAmt.compareTo(totalAmt) > 0) {
            throw new IllegalArgumentException("Paid amount cannot be greater than total amount.");
        }

        UserListEntity userDetails = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User detail not found"));

        BigDecimal paidAmt = bookingDetails.getPaidAmt().add(dto.getPaidAmount());
        bookingDetails.setPaidAmt(paidAmt);
        BigDecimal pendingAmt = bookingDetails.getPendingAmt().subtract(dto.getPaidAmount());
        bookingDetails.setPendingAmt(pendingAmt);
        bookingDetails.setUpdatedAt(LocalDateTime.now());
        if (paidAmt.equals(bookingDetails.getTotalAmount())) {
            bookingDetails.setPaymentStatus(PaymentStatus.PAID);
        }

        entity.setBooking(bookingDetails.getBookingId());
        entity.setUser(userDetails.getUserId());
        entity.setPaymentMode(dto.getPaymentMode());
        entity.setTotalAmount(bookingDetails.getTotalAmount());
        entity.setPaidAmount(dto.getPaidAmount());
        entity.setPendingAmount(pendingAmt);
        entity.setPaymentReference(dto.getPaymentReference());
        entity.setPaymentDate(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        BookingAmtEntity save = bookingAmtRepository.save(entity);
        return save;

    }


}
