package net36px.passport.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net36px.passport.boot.rest.web.service.WebSessionService;
import net36px.passport.boot.rest.web.service.WebSignInService;
import net36px.passport.boot.rest.web.service.WebSignUpService;
import net36px.passport.boot.rest.web.service.impl.WebSessionServiceImpl;
import net36px.passport.boot.rest.web.service.impl.WebSignInServiceImpl;
import net36px.passport.boot.rest.web.service.impl.WebSignUpServiceImpl;

@Configuration

public class ConfigWebREST {

	@Bean
	public WebSignUpService web_sign_up() {
		return new WebSignUpServiceImpl();
	}

	@Bean
	public WebSignInService web_sign_in() {
		return new WebSignInServiceImpl();
	}

	@Bean
	public WebSessionService web_session() {
		return new WebSessionServiceImpl();
	}

}
