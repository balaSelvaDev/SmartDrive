package mca.finalyearproject.smartDrive.SmartDrive.Repository;

import mca.finalyearproject.smartDrive.SmartDrive.Entity.VehicleEntity;
import mca.finalyearproject.smartDrive.SmartDrive.InterfaceProjection.VehicleAvailabilityByVehicleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {

    List<VehicleEntity> findByVehicleNameContainingIgnoreCase(String vehicleName);

    @Query(value = "" +
            "SELECT distinctrow \n" +
            "    CASE WHEN (\n" +
            "    (:userPickupDatetime BETWEEN b.start_date AND b.end_date) or \n" +
            "            (:userDropDatetime BETWEEN b.start_date AND b.end_date) or\n" +
            "            (:userPickupDatetime <= b.start_date AND :userDropDatetime >= b.end_date)\n" +
            "\t) THEN 'BOOKED'\n" +
            "\tELSE 'AVAILABLE'\n" +
            "    END AS availabilityStatus, v.booking_status as bookingStatus, v.vehicle_id as vehicleId,\n" +
            "    v.vehicle_name as vehicleName, v.year_of_manufacture as yearOfManufacture, v.price_per_km as pricePerKm,\n" +
            "    v.mileage_per_litre as mileagePerLitre, v.vehicle_type as vehicleType, v.fuel_type as fuelType,\n" +
            "    v.seating_capacity as seatingCapacity, \n" +
            "    v.model_id as model_id,\n" +
            "    (SELECT model_name FROM vehicle_model where model_id = v.model_id) as model_name,\n" +
            "    (SELECT brand_id FROM vehicle_model where model_id = v.model_id) as brand_id,\n" +
            "    (SELECT brand_name FROM brand_list where brand_id = (SELECT brand_id FROM vehicle_model where model_id = v.model_id)) as brand_name " +
            "FROM vehicle v\n" +
            "\tleft join booking b  on v.vehicle_id = b.vehicle_id \n" +
            "where v.is_visible_online = :isVisibleOnline and v.vehicle_status = :vehicleStatus " +
            "",
            countQuery = "SELECT COUNT(*) FROM (SELECT \n" +
                    "    CASE WHEN (\n" +
                    "(:userPickupDatetime BETWEEN b.start_date AND b.end_date) or \n" +
                    "            (:userDropDatetime BETWEEN b.start_date AND b.end_date) or\n" +
                    "            (:userPickupDatetime <= b.start_date AND :userDropDatetime >= b.end_date)\n" +
                    "\t) THEN 'BOOKED'\n" +
                    "\tELSE 'AVAILABLE'\n" +
                    "    END AS availabilityStatus \n" +
                    "FROM vehicle v\n" +
                    "\tleft join booking b  on v.vehicle_id = b.vehicle_id \n" +
                    "where v.is_visible_online = :isVisibleOnline and v.vehicle_status = :vehicleStatus )t1 ",
            nativeQuery = true)
    Page<Object[]> findVehiclesWithDetailedAvailability(
            LocalDateTime userPickupDatetime,
            LocalDateTime userDropDatetime,
            Boolean isVisibleOnline,
            String vehicleStatus,
            Pageable pageable
    );

    @Query(value = "" +
            "SELECT \n" +
            "    CASE WHEN (\n" +
            "    (:userPickupDatetime BETWEEN b.start_date AND b.end_date) or \n" +
            "            (:userDropDatetime BETWEEN b.start_date AND b.end_date) or\n" +
            "            (:userPickupDatetime <= b.start_date AND :userDropDatetime >= b.end_date)\n" +
            "\t) THEN 'BOOKED'\n" +
            "\tELSE 'AVAILABLE'\n" +
            "    END AS availabilityStatus, v.booking_status as bookingStatus, v.vehicle_id as vehicleId,\n" +
            "    v.vehicle_name as vehicleName \n" +
            "FROM vehicle v\n" +
            "\tleft join booking b  on v.vehicle_id = b.vehicle_id \n" +
            "where v.vehicle_status = :vehicleStatus and v.vehicle_id = :vehicleId " +
            "",
            nativeQuery = true)
    VehicleAvailabilityByVehicleId getVehicleAvailabilityByVehicleIdAndDateTime(
            LocalDateTime userPickupDatetime,
            LocalDateTime userDropDatetime,
            String vehicleStatus,
            Integer vehicleId
    );

}
