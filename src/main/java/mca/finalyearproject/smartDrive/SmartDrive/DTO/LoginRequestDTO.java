package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.AuthProvider;

public class LoginRequestDTO {

    private String emailId;
    private String password;
    private String authProvider;

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }
// --- Getters and Setters ---

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", authProvider=" + authProvider +
                '}';
    }
}
