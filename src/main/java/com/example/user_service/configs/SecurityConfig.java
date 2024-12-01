package com.example.user_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF protection (only if you're building a stateless application, e.g., with REST APIs)
                .cors().disable()  // Disable CORS (configure it later if needed)
                .authorizeRequests()
                .anyRequest().permitAll()  // Allow all requests without any authentication or authorization
                .and()
                .httpBasic().disable()  // Disable HTTP Basic authentication (optional, depending on your needs)
                .formLogin().disable();  // Disable form-based login (optional)

        return http.build();
    }
}
