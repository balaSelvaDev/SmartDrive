package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VehicleResponseDTO {

    private Integer vehicleId;
    private VehicleModelResponseDTO vehicleModelResponseDTO;
    private String vehicleName;
    private String description;
    private String registrationNo;
    private BigDecimal pricePerKm;
    private BigDecimal convenienceFee;
    private BigDecimal refundableAmt;
    private FuelType fuelType;
    private Integer fuelCapacity;
    private Double mileagePerLitre;
    private Integer seatingCapacity;
    private String color;
    private VehicleType vehicleType;
    private OwnerType ownerType;
    private Integer yearOfManufacture;
    private Integer engineCc;
    private String torque;
    private Integer horsepower;
    private Boolean isInsured;
    private LocalDateTime insuranceExpiryDate;
    private LocalDateTime lastUpdatedInsuranceDate;
    private Boolean hasAirbags;
    private Boolean hasAbs;
    private Boolean hasSunroof;
    private Boolean hasGps;
    private Boolean hasMusicSystem;
    private Boolean hasReverseCamera;
    private VehicleStatus vehicleStatus;
    private Boolean available;
    private Boolean isVisibleOnline;
    private ClientLocationResponseDTO clientLocation;
    private List<VehicleImageResponseDTO> vehicleImagesList;
    private BookingStatus bookingStatus;
    private TransportType transportType;

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Boolean getVisibleOnline() {
        return isVisibleOnline;
    }

    public void setVisibleOnline(Boolean visibleOnline) {
        isVisibleOnline = visibleOnline;
    }

    public BigDecimal getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(BigDecimal convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public BigDecimal getRefundableAmt() {
        return refundableAmt;
    }

    public void setRefundableAmt(BigDecimal refundableAmt) {
        this.refundableAmt = refundableAmt;
    }

    public List<VehicleImageResponseDTO> getVehicleImagesList() {
        return vehicleImagesList;
    }

    public void setVehicleImagesList(List<VehicleImageResponseDTO> vehicleImagesList) {
        this.vehicleImagesList = vehicleImagesList;
    }

    public ClientLocationResponseDTO getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(ClientLocationResponseDTO clientLocation) {
        this.clientLocation = clientLocation;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleModelResponseDTO getVehicleModelResponseDTO() {
        return vehicleModelResponseDTO;
    }

    public void setVehicleModelResponseDTO(VehicleModelResponseDTO vehicleModelResponseDTO) {
        this.vehicleModelResponseDTO = vehicleModelResponseDTO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Integer fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Double getMileagePerLitre() {
        return mileagePerLitre;
    }

    public void setMileagePerLitre(Double mileagePerLitre) {
        this.mileagePerLitre = mileagePerLitre;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Integer getEngineCc() {
        return engineCc;
    }

    public void setEngineCc(Integer engineCc) {
        this.engineCc = engineCc;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Boolean getInsured() {
        return isInsured;
    }

    public void setInsured(Boolean insured) {
        isInsured = insured;
    }

    public LocalDateTime getInsuranceExpiryDate() {
        return insuranceExpiryDate;
    }

    public void setInsuranceExpiryDate(LocalDateTime insuranceExpiryDate) {
        this.insuranceExpiryDate = insuranceExpiryDate;
    }

    public LocalDateTime getLastUpdatedInsuranceDate() {
        return lastUpdatedInsuranceDate;
    }

    public void setLastUpdatedInsuranceDate(LocalDateTime lastUpdatedInsuranceDate) {
        this.lastUpdatedInsuranceDate = lastUpdatedInsuranceDate;
    }

    public Boolean getHasAirbags() {
        return hasAirbags;
    }

    public void setHasAirbags(Boolean hasAirbags) {
        this.hasAirbags = hasAirbags;
    }

    public Boolean getHasAbs() {
        return hasAbs;
    }

    public void setHasAbs(Boolean hasAbs) {
        this.hasAbs = hasAbs;
    }

    public Boolean getHasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof(Boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    public Boolean getHasGps() {
        return hasGps;
    }

    public void setHasGps(Boolean hasGps) {
        this.hasGps = hasGps;
    }

    public Boolean getHasMusicSystem() {
        return hasMusicSystem;
    }

    public void setHasMusicSystem(Boolean hasMusicSystem) {
        this.hasMusicSystem = hasMusicSystem;
    }

    public Boolean getHasReverseCamera() {
        return hasReverseCamera;
    }

    public void setHasReverseCamera(Boolean hasReverseCamera) {
        this.hasReverseCamera = hasReverseCamera;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
