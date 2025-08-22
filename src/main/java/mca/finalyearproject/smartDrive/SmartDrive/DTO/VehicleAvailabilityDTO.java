package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.BookingStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.FuelType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleType;

import java.math.BigDecimal;
import java.util.List;

public class VehicleAvailabilityDTO {

    private String availabilityStatus;
    private Integer vehicleId;
    private String bookingStatus;   // Use enum if consistent
    private String vehicleName;
    private Integer yearOfManufacture;
    private String pricePerKm;
    private String mileagePerLitre;
    private String vehicleType;     // Use enum if consistent
    private String fuelType;        // Use enum if consistent
    private Integer seatingCapacity;
    private List<VehicleImageResponseDTO>  vehicleImageList;

    private Integer modelId;
    private String modelName;
    private Integer brandId;
    private String brandName;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<VehicleImageResponseDTO> getVehicleImageList() {
        return vehicleImageList;
    }

    public void setVehicleImageList(List<VehicleImageResponseDTO> vehicleImageList) {
        this.vehicleImageList = vehicleImageList;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(String pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public String getMileagePerLitre() {
        return mileagePerLitre;
    }

    public void setMileagePerLitre(String mileagePerLitre) {
        this.mileagePerLitre = mileagePerLitre;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
