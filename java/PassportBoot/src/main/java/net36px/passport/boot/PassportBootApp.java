package net36px.passport.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication

@Import({ PassportConfig.class })

public class PassportBootApp {

	public static void main(String[] args) {
		SpringApplication.run(PassportBootApp.class, args);
	}

}
