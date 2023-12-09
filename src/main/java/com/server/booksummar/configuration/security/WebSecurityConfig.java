package com.server.booksummar.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bookSummary/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/bookSummary/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/bookSummary/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/bookSummary").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/bookSummary").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/bookSummary/userId/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/bookSummary/booName/{booName}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/bookSummary/bookAuthor/{bookAuthor}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/bookSummary/sendSummaryEmail/{bookId}/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/bookSummary/commentBookSummary").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/bookSummary/likeBookSummary/{bookId}/{userId}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/user/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/user/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/requestNewPassword").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
