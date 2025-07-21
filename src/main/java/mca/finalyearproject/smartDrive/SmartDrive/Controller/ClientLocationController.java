package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.DTO.ClientLocationResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Entity.ClientLocationEntity;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.ClientLocationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client-location")
public class ClientLocationController {

    @Autowired
    private ClientLocationImpl clientLocation;

    @GetMapping
    public List<ClientLocationResponseDTO> getAllClientLocation(@RequestParam Integer districtId) {
        return clientLocation.getAllClientLocation(districtId);
    }


}
