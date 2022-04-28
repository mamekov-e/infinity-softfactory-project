package com.shop.onlineshop.config;

import com.shop.onlineshop.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final CustomOidcUserService customOidcUserService;
	
	private UserPrincipalDetailService userPrincipalDetailService;
	
	public SpringSecurityConfiguration(UserPrincipalDetailService userPrincipalDetailService, @Lazy CustomOidcUserService customOidcUserService) {
		this.userPrincipalDetailService = userPrincipalDetailService;
		this.customOidcUserService = customOidcUserService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index","/signup","/login","/resources/**").permitAll()
				.antMatchers("/profile/**").authenticated()
//				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/oauth/**").permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutSuccessUrl("/login")
			.and()
			.formLogin().loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
				.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.oidcUserService(customOidcUserService);
//			.and()
//			.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.invalidateHttpSession(true)
//				.clearAuthentication(true)
//				.logoutSuccessUrl("/login");


//				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout");
//			.formLogin()
//			.loginPage("/login")
//			.usernameParameter("email")
//			.passwordParameter("password")
//			.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			.logoutSuccessUrl("/login")
//			.and()
//			.rememberMe().tokenValiditySeconds(30000).key("WhatEver!")
//			.rememberMeParameter("checkRememberMe");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomOidcUserService customOidcUserService() {
		return new CustomOidcUserService();
	}
}
