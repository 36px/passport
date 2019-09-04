package net36px.passport.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net36px.passport.boot.data.service.AccountService;
import net36px.passport.boot.data.service.AuthService;
import net36px.passport.boot.data.service.MailToService;
import net36px.passport.boot.data.service.RandomStringService;
import net36px.passport.boot.data.service.SettingService;
import net36px.passport.boot.data.service.TicketService;
import net36px.passport.boot.data.service.impl.AccountServiceImpl;
import net36px.passport.boot.data.service.impl.AuthServiceImpl;
import net36px.passport.boot.data.service.impl.MailToServiceImpl;
import net36px.passport.boot.data.service.impl.RandomStringServiceImpl;
import net36px.passport.boot.data.service.impl.SettingServiceImpl;
import net36px.passport.boot.data.service.impl.TicketServiceImpl;

@Configuration

public class ConfigDataService {

	@Bean
	public MailToService mail_to_service() {
		return new MailToServiceImpl();
	}

	@Bean
	public SettingService setting_service() {
		return new SettingServiceImpl();
	}

	@Bean
	public TicketService transactor_service() {
		return new TicketServiceImpl();
	}

	@Bean
	public AccountService account_service() {
		return new AccountServiceImpl();
	}

	@Bean
	public AuthService auth_service() {
		return new AuthServiceImpl();
	}

	@Bean
	public RandomStringService random_string_service() {
		return new RandomStringServiceImpl();
	}

}
