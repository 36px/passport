package net36px.passport.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net36px.passport.boot.rest.app.service.AppSignInService;
import net36px.passport.boot.rest.app.service.impl.AppSignInServiceImpl;

@Configuration

public class ConfigAppREST {

	@Bean
	public AppSignInService app_sign_in() {
		return new AppSignInServiceImpl();
	}

}
