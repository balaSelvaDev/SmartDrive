package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class ResetDetailsResponseDTO {

    private String uuid;
    private String emailId;
    private Integer userId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public String toString() {
        return "ResetDetailsResponseDTO{" +
                "uuid='" + uuid + '\'' +
                ", emailId='" + emailId + '\'' +
                ", userId=" + userId +
                '}';
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
}
