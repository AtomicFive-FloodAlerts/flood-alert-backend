package Atomic5.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FloodAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloodAlertsApplication.class, args);
		System.out.println("Starting Flood Alert System Backend...");
		System.out.println("Flood Alert System Backend is running on port 8081...");
		
	}

	@GetMapping("")
	public String Welcome() {
		return "Hello World - Flood Alert System Running";
	}

	@GetMapping("/health")
	public String health() {
		return "Flood Alert Backend is healthy";
	}

}
