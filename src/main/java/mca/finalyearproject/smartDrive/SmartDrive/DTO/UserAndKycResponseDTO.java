package mca.finalyearproject.smartDrive.SmartDrive.DTO;

public class UserAndKycResponseDTO {

    private UserDetailsResponseDTO userDetailsResponseDTO;
    private UserKycDetailsResponseDTO userKycDetailsResponseDTO;

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
