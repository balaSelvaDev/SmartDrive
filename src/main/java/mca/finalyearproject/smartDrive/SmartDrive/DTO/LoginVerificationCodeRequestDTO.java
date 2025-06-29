package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class LoginVerificationCodeRequestDTO {

    private String uuid;
    private String emailId;
    private Integer userId;
    private String code;

    // --- Getters and Setters ---

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
