package com.bus.BusService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAutheticationFilter jwtAutheticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //http.csrf(csrf-> csrf.disable());  This way we would be doing from Spring 3 versions ownwards
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeRequests(
                		requests -> 
                			requests.antMatchers("/registerBus/**").authenticated()
                				.antMatchers("/ownerRegister/**").permitAll()
                					.anyRequest().authenticated())
                       .exceptionHandling(ex-> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                       .sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
        http.addFilterBefore(jwtAutheticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
	}

}
