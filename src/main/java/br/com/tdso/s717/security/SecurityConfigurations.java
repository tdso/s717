package br.com.tdso.s717.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/acao").permitAll()
		.antMatchers(HttpMethod.GET, "/acao/*").permitAll()
		.antMatchers(HttpMethod.GET, "/neg").permitAll()
		.antMatchers(HttpMethod.GET, "/neg/*").permitAll()
		//.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and()
        .csrf().disable();
		//.and().formLogin();
	}
}
