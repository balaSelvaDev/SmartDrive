package mca.finalyearproject.smartDrive.SmartDrive.Entity;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.FuelType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.OwnerType;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleStatus;
import mca.finalyearproject.smartDrive.SmartDrive.Enum.VehicleType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModelEntity model;

    @Column(length = 1000)
    private String description;

    @Column(name = "registration_no", unique = true, nullable = false)
    private String registrationNo;

    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    private Integer fuelCapacity;

    private Double mileagePerLitre;

    private Integer seatingCapacity;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
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

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    private Boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleModelEntity getModel() {
        return model;
    }

    public void setModel(VehicleModelEntity model) {
        this.model = model;
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

    public Double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Double pricePerKm) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}