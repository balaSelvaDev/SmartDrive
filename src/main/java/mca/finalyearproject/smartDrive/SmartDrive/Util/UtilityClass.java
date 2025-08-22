package mca.finalyearproject.smartDrive.SmartDrive.Util;


import mca.finalyearproject.smartDrive.SmartDrive.Config.OrderNotification;
import mca.finalyearproject.smartDrive.SmartDrive.Security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

@Component
public class UtilityClass {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public String createRandomCode() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

    public String createUuidCode() {
        return java.util.UUID.randomUUID().toString();
    }

    public String createFullName(String first_name, String last_name) {
        return first_name + " " + last_name;
    }

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("1: "+authentication);
        if (authentication != null && authentication.isAuthenticated()) {
//            System.out.println("2: "+authentication);
            for (GrantedAuthority authority : authentication.getAuthorities()) {
//                System.out.println("3: "+authority);
                return authority.getAuthority(); // e.g., "ROLE_ADMIN"
            }
        }
        return null;
    }

    public void sendNotificationToAdmin(String authHeader, Integer Id) {
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
            if (!"ROLE_ADMIN".equals(getCurrentUserRole())) {
                String message = "Created by " + username + "(" + userId + ")";
                OrderNotification notification = new OrderNotification(message, Long.valueOf(Id));
                messagingTemplate.convertAndSend("/topic/admin-orders", notification);
            }
        }
    }

}
