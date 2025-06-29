package mca.finalyearproject.smartDrive.SmartDrive.Util;

import lombok.extern.apachecommons.CommonsLog;
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



}
