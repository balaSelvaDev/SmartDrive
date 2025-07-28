package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.FuelType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.OwnerType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleType;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleAddRequestDTO {

    private String vehicleName;
    private Integer modelId;
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
    private Integer clientLocationId;
    private Boolean isVisibleOnline;

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

    public Integer getClientLocationId() {
        return clientLocationId;
    }

    public void setClientLocationId(Integer clientLocationId) {
        this.clientLocationId = clientLocationId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
