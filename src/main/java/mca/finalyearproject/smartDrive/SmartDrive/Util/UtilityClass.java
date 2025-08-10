package mca.finalyearproject.smartDrive.SmartDrive.Util;


import org.springframework.stereotype.Component;

import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

@Component
public class UtilityClass {

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


}
