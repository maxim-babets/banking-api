package com.banking.api.config;

import com.banking.api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**","/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .anyRequest().authenticated()).csrf(csrf -> csrf.disable())
                        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
