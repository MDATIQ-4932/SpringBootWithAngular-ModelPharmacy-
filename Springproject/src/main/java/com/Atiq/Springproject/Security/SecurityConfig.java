package com.Atiq.Springproject.Security;


import com.Atiq.Springproject.Service.UserService;
import com.Atiq.Springproject.jwt.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private  UserService userService;
    private  JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http,
//                                           JwtAuthenticationFilter jwtAuthenticationFilter,
//                                           UserService userService) throws Exception {
//
//        return
//                http
//                        .csrf(AbstractHttpConfigurer::disable)
//                        .cors(Customizer.withDefaults())
//                        .authorizeHttpRequests(
//
//                                req ->
//                                        req.requestMatchers(
//                                                "/api/register",
//                                                "/api/register/**",
//                                                "/api/login",
//                                                "/api/activate/**",
//                                                "/images/**"
//                                        ).permitAll()
//                                                .requestMatchers("api/product/save","api/product/", "api/category/save","/api/branch/save")
//                                                .hasAuthority("ADMIN")
//                                                .requestMatchers( "api/medicine/{id}","api/pharmacist/all/**", "api/location/")
//                                                .hasAnyAuthority("ADMIN", "PHARMACIST")
//                                                .requestMatchers("api/user/**")
//                                                .hasAuthority("USER")
//                                                .requestMatchers("/images/**").permitAll()
//
//                        )
//                        .userDetailsService(userService)
//                        .sessionManagement(
//                                session ->
//                                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        )
//                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                        .build();
//
//
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           UserService userService) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/register",
                                "/api/register/**",
                                "/api/login",
                                "/api/activate/**",
                                "/api/branch/**",
                                "/api/category/**",
                                "/api/customer/**",
                                "/api/product/**",
                                "/api/salesdetails/**",
                                "/api/sales/**",
                                "/api/supplier/**",
                                "/images/**",
                                "/api/product/**",
                                "/api/product/update/**"

                        ).permitAll()
                        .requestMatchers( "/api/category/save", "/api/branch/save")
                        .hasAuthority("ADMIN")
                        .requestMatchers("/api/medicine/**", "/api/pharmacist/all/**", "/api/location/**")
                        .hasAnyAuthority("ADMIN", "PHARMACIST")
                        .requestMatchers("/api/user/**", "/api/product/save")
                        .hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:5001"));  // Add allowed origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Apply CORS settings to all endpoints
        return source;
    }




}
