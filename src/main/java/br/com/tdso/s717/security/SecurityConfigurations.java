package br.com.tdso.s717.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		
		//.antMatchers(HttpMethod.GET, "/h2-console").permitAll()
		//.antMatchers(HttpMethod.GET, "/h2-console/*").permitAll()
//		.antMatchers(HttpMethod.GET, "/acao").permitAll()
//		.antMatchers(HttpMethod.GET, "/acao/*").permitAll()
		.antMatchers("/acao/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/acao").permitAll()
		.antMatchers(HttpMethod.DELETE, "/acao/*").permitAll()
		.antMatchers("/neg/**").permitAll()
//		.antMatchers(HttpMethod.GET, "/neg").permitAll()
//		.antMatchers(HttpMethod.GET, "/neg/*").permitAll()
		.anyRequest().authenticated()
		.and()
        .csrf().disable()
		.headers().frameOptions().sameOrigin();
		//.and().formLogin();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT","DELETE","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
   }
	
}
