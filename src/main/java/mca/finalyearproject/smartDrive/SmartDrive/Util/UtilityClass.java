package mca.finalyearproject.smartDrive.SmartDrive.Util;


import org.springframework.stereotype.Component;

import java.util.Random;

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

}
