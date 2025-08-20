package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.BookingEntity;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.BrandIdAndNameResponse;
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

}
