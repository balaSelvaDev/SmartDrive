package mca.finalyearproject.smartDrive.SmartDrive.Controller;

import mca.finalyearproject.smartDrive.SmartDrive.Config.OrderNotification;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameAndVMIdNameResponseDTO;
import mca.finalyearproject.smartDrive.SmartDrive.DTO.BrandIdNameRequestDTO;
import mca.finalyearproject.smartDrive.SmartDrive.Security.JWTProvider;
import mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl.BrandServiceImpl;
import mca.finalyearproject.smartDrive.SmartDrive.Util.PaginationResponse;
import mca.finalyearproject.smartDrive.SmartDrive.Util.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UtilityClass utilityClass;

    @Autowired
    private JWTProvider jwtProvider;

    @GetMapping
    public ResponseEntity<PaginationResponse<BrandDTO>> getAllBrands(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(brandService.getAllBrands(page, size));
    }

    @GetMapping("/{brandId}")
    public BrandDTO getParticularBrand(@PathVariable("brandId") Integer brandId) {
        return brandService.getParticularBrand(brandId);
    }

    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO brandDTO, HttpServletRequest request) {
        BrandDTO brand = brandService.createBrand(brandDTO);

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // remove "Bearer "
            // Now you can call your JWT util methods
//            System.out.println("JWT");
            String username = jwtProvider.getUsernameFromToken(token);
//            System.out.println("username: "+username);
            List<String> roles = jwtProvider.getRolesFromToken(token);
//            System.out.println("roles: "+roles);
            Long userId = jwtProvider.getUserIdFromToken(token);
//            System.out.println("userId: "+userId);
            if (!"ROLE_ADMIN".equals(utilityClass.getCurrentUserRole())) {
                String message = "Created by " + username + "(" + userId + ")";
//                System.out.println(message);
                OrderNotification notification = new OrderNotification(message, Long.valueOf(brand.getBrandId()));
                messagingTemplate.convertAndSend("/topic/admin-orders", notification);
            }

        }


        return brand;
    }

    @PutMapping
    public BrandDTO updateBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(brandDTO);
    }

    @DeleteMapping("/{brandId}")
    public void deleteBrand(@PathVariable("brandId") Integer brandId) {
        brandService.deleteBrand(brandId);
    }

    @GetMapping("/brand-name")
    public List<BrandIdNameRequestDTO> getBrandName(@RequestParam(required = false) String search,
                                                    @RequestParam(defaultValue = "5") int limit) {
        return brandService.getBrandName(search, limit);
    }

    @GetMapping("/search/brandname")
    public List<BrandIdNameAndVMIdNameResponseDTO> getBrandIdNameAndVehicleModelIdName(@RequestParam(required = false, value = "brandname") String brandname,
                                                                                       @RequestParam(defaultValue = "5") int limit) {
        return brandService.getBrandIdNameAndVehicleModelIdName(brandname, limit);
    }


}