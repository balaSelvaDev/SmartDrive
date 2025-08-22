package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.BookingEntity;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.BookingDetailsResponse;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.BrandIdAndNameResponse;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.GetCountsDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    boolean existsByBookingReference(String bookingReference);

    @Query(value = "" +
            "SELECT brand_id as brandId, brand_name, brandName FROM smartdrive.brand_list where brand_id in (\n" +
            "\tSELECT brand_id FROM smartdrive.vehicle_model \n" +
            "    where model_id in (\n" +
            "\t\tSELECT model_id FROM smartdrive.vehicle where is_visible_online = 1 and is_active != 0\n" +
            "\t)\n" +
            ");" +
            "", nativeQuery = true)
    List<BrandIdAndNameResponse> getBrandIdAndNameList();

    // dashboard queries
    @Query(value = "" +
            "SELECT (\n" +
            "\tSELECT COUNT(*) FROM user_list t1 left join user_kyc_details t2 on t1.user_id = t2.user_id where t1.is_active != 0\n" +
            ") as userCount, (\n" +
            "\tSELECT COUNT(*) FROM vehicle where is_active != 0\n" +
            ") as vehicleCount" +
            "", nativeQuery = true)
    GetCountsDetails getCountsDetails();

    @Query(value = "" +
            "SELECT \n" +
            "\tfull_name as fullName, start_date as startDate, end_date as endDate, pickup_location as pickupLocation, \n" +
            "    drop_location as dropLocation, payment_status as paymentStatus, booking_status as bookingStatus\n" +
            "FROM booking t1 left join user_list t2 on t1.user_id = t2.user_id" +
            "", nativeQuery = true)
    List<BookingDetailsResponse> getBookingDetails();

}
