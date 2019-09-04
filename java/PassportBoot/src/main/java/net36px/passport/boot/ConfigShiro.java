package net36px.passport.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net36px.passport.boot.security.AuthToAccountRealm;

@Configuration

public class ConfigShiro {

	@Bean
	public Realm basic_realm() {
		return new AuthToAccountRealm();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(WebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		// filtersMap.put("roleOrFilter", roleOrFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		filterChainDefinitionMap.put("/web.api/SignIn/**", "anon");
		filterChainDefinitionMap.put("/web.api/SignUp/**", "anon");
		filterChainDefinitionMap.put("/web.api/Session/**", "anon");

		filterChainDefinitionMap.put("/web.api/**", "user");
		filterChainDefinitionMap.put("/app.api/**", "anon");
		// filterChainDefinitionMap.put("/**", "roleOrFilter[admin,admin1]");

		shiroFilterFactoryBean.setLoginUrl(null);
		shiroFilterFactoryBean.setSuccessUrl(null);
		shiroFilterFactoryBean.setUnauthorizedUrl(null);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public WebSecurityManager webSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(this.basic_realm());
		// securityManager.setCacheManager(ehCacheManager());
		// securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("sha-256");
		hashedCredentialsMatcher.setHashIterations(5);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(this.webSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/***************
	 * 授权器
	 */

	@Bean
	public Authorizer authorizer() {
		return new ModularRealmAuthorizer();
	}

	/***************
	 * 验证器
	 */

	@Bean
	public Authenticator authenticator() {
		return new ModularRealmAuthenticator();
	}

	@Bean
	public WebSessionManager sessionManager() {
		return new DefaultWebSessionManager();
	}

}
