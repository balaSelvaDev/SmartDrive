package mca.finalyearproject.smartDrive.SmartDrive.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String fromNumber;

    public void sendSms(String toNumber, String message) {
//        Message.creator(
//                new PhoneNumber(toNumber),
//                new PhoneNumber(fromNumber),
//                message
//        ).create();
//        Verification verification = Verification.creator(
//                        "VAc6e14463078aba2e8fe44c4ff71e080f",
//                        toNumber,
//                        "sms")
//                .create();
        Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(fromNumber),  // Must be your Twilio number
                        "Your SmartDrive OTP is 842513")
                .create();
    }
}