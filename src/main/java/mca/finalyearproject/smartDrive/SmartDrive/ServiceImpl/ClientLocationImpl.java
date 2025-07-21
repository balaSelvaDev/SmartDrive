package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.ClientLocationResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.ClientLocationEntity;
import mca.finalyearproject.smartDrive.SmartDrive.Repository.ClientLocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientLocationImpl {

    @Autowired
    private ClientLocationRepository clientLocationRepository;

    public List<ClientLocationResponseDTO> getAllClientLocation(Integer districtId) {
        return clientLocationRepository.findByIsActiveAndDistrictId(true, districtId).stream().map(this::entityToClientLocationDto).collect(Collectors.toList());
    }

    private ClientLocationResponseDTO entityToClientLocationDto(ClientLocationEntity entity) {
        ClientLocationResponseDTO dto = new ClientLocationResponseDTO();
        dto.setClientLocationId(entity.getClientLocationId());
        dto.setLocationName(entity.getLocationName());
        dto.setDisplayName(entity.getDisplayName());
        dto.setAddress(entity.getAddress());
        dto.setPincode(entity.getPincode());
        dto.setActive(entity.getActive());
        return dto;

    }

}
