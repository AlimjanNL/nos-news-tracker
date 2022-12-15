package nl.alimjan.demo.nosnewstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NosNewsTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosNewsTrackerApplication.class, args);
	}

}
