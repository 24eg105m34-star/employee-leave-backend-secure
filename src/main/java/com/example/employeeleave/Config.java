package com.example.employeeleave;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
public class Config {
 @org.springframework.beans.factory.annotation.Value("${app.cors.allowed-origin}") private String origin;
 @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(12); }
 @Bean public CorsConfigurationSource corsConfigurationSource(){
  CorsConfiguration c=new CorsConfiguration(); c.setAllowedOrigins(List.of(origin,"http://127.0.0.1:5173"));
  c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
  c.setAllowedHeaders(List.of("Authorization","Content-Type")); c.setExposedHeaders(List.of("Authorization"));
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource(); s.registerCorsConfiguration("/**",c); return s;
 }
}
