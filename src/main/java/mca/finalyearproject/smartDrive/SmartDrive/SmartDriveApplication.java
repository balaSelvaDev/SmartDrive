package mca.finalyearproject.smartDrive.SmartDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;
import java.util.Base64;

@EnableConfigurationProperties
@SpringBootApplication
public class SmartDriveApplication {
	// kfxn lnjk vomr wlqm
	public static void main(String[] args) {
		SpringApplication.run(SmartDriveApplication.class, args);
//		SecureRandom random = new SecureRandom();
//		byte[] keyBytes = new byte[64]; // 512 bits for HMAC512
//		random.nextBytes(keyBytes);
//		String secretKey = Base64.getEncoder().encodeToString(keyBytes);
//		System.out.println("Secure Secret Key: " + secretKey);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
