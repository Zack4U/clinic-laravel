package com.clinica.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.clinica.demo.service.CustomOAuth2UserService;
import com.clinica.demo.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Bean
	SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/").permitAll();
					auth.anyRequest().authenticated();
				})
				.oauth2Login(oauth2 -> oauth2.userInfoEndpoint()
						.userService(customOAuth2UserService))
				.logout(logout -> logout.logoutSuccessUrl("/")) // this is the URL to redirect to after logout
				.build();
	}
}
