package mca.finalyearproject.smartDrive.SmartDrive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SmartDriveApplicationTests {

	@Test
	void contextLoads() {
		BigDecimal a = new BigDecimal("34339.00");
		BigDecimal b = new BigDecimal("34339.00");

		// Check if a <= b
		if (a.compareTo(b) <= 0) {
			System.out.println("a is less than or equal to b");
		} else {
			System.out.println("a is greater than b");
		}
	}

}
