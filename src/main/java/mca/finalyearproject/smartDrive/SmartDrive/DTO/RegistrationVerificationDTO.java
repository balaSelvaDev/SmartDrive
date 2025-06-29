package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class RegistrationVerificationDTO {

    private String uuid;
    private Integer code;
    private Integer userId;
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
// --- Constructors ---

    public RegistrationVerificationDTO() {
    }

    public RegistrationVerificationDTO(String uuid, Integer code, Integer userId) {
        this.uuid = uuid;
        this.code = code;
        this.userId = userId;
    }

    // --- Getters & Setters ---

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}