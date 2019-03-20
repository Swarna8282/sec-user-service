package com.fedex.sad;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure (HttpSecurity hSec) throws Exception {
		hSec.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/all").permitAll()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/all/*").permitAll()
				.antMatchers(HttpMethod.GET, "/*").permitAll()
				.antMatchers(HttpMethod.POST, "/all").hasAnyRole("ADMINS","MANAGERS")
				.antMatchers(HttpMethod.DELETE, "/all/*").hasRole("ADMINS")
				.anyRequest().authenticated()
			.and()
			.httpBasic().and().csrf().disable();
		
		hSec.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout") // "/login?logout" is the default value for the logoutSuccessUrl method
			.invalidateHttpSession(true)
			.deleteCookies("SESSION");
			
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder amBuilder) throws Exception {
		amBuilder.inMemoryAuthentication();
	}

}
