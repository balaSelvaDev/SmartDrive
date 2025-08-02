package mca.finalyearproject.smartDrive.SmartDrive.DTO;

import mca.finalyearproject.smartDrive.SmartDrive.Enum.KycImageType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAndKycResponseDTO {

    private UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
    private UserKycDetailsResponseDTO userKycDetailsResponseDTO = new UserKycDetailsResponseDTO();
    private Map<KycImageType, List<UserKycImageResponseDTO>> userKycImageResponseDTO = new HashMap<>();
    private LocalDateTime lastLoginTime;
    private String role;

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<KycImageType, List<UserKycImageResponseDTO>> getUserKycImageResponseDTO() {
        return userKycImageResponseDTO;
    }

    public void setUserKycImageResponseDTO(Map<KycImageType, List<UserKycImageResponseDTO>> userKycImageResponseDTO) {
        this.userKycImageResponseDTO = userKycImageResponseDTO;
    }

    public UserDetailsResponseDTO getUserDetailsResponseDTO() {
        return userDetailsResponseDTO;
    }

    public void setUserDetailsResponseDTO(UserDetailsResponseDTO userDetailsResponseDTO) {
        this.userDetailsResponseDTO = userDetailsResponseDTO;
    }

    public UserKycDetailsResponseDTO getUserKycDetailsResponseDTO() {
        return userKycDetailsResponseDTO;
    }

    public void setUserKycDetailsResponseDTO(UserKycDetailsResponseDTO userKycDetailsResponseDTO) {
        this.userKycDetailsResponseDTO = userKycDetailsResponseDTO;
    }
}
