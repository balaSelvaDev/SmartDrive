package mca.finalyearproject.smartDrive.SmartDrive.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationCode(String toEmail, String code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + code);
        mailSender.send(message);

    }

    public void sendVerificationEmail(String toEmail, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject("Email Verification Code");

        // HTML content using String concatenation for Java 8
        String htmlContent =
                "<html>" +
                        "<body style='margin: 0; padding: 0; background-color: #ffffff;'>" +
                        "<table align='center' width='100%' height='100%' cellpadding='0' cellspacing='0' style='text-align: center;'>" +
                        "<tr>" +
                        "<td>" +
                        "<h2 style='color: #0033cc; font-family: Arial, sans-serif;'>SmartDrive</h2>" +

                        "<div style='background-color: #cdd7e5; max-width: 400px; margin: 0 auto; padding: 30px 20px; border-radius: 12px; font-family: Arial, sans-serif;'>" +
                        "<p style='font-size: 16px; color: #333;'>Hi Bala,</p>" +
                        "<p style='font-size: 16px; color: #333;'>Here's your verification code:</p>" +
                        "<h1 style='font-size: 32px; color: #000; margin: 10px 0;'>" + code + "</h1>" +
                        "<p style='font-size: 14px; color: #555;'>If you didn’t request this, ignore this email.</p>" +
                        "<br/>" +
                        "<p style='font-size: 14px; color: #333;'>Thanks,<br/>The SmartDrive Team</p>" +
                        "</div>" +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</body>" +
                        "</html>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}