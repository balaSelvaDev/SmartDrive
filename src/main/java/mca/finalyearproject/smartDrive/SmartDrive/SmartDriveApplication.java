package mca.finalyearproject.smartDrive.SmartDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableConfigurationProperties
@SpringBootApplication
public class SmartDriveApplication {
	// kfxn lnjk vomr wlqm
	public static void main(String[] args) {
		SpringApplication.run(SmartDriveApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
