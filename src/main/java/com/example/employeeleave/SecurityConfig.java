package com.example.employeeleave;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity public class SecurityConfig {
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtAuthFilter jwtFilter) throws Exception {
  return http.csrf(csrf->csrf.disable()).cors(Customizer.withDefaults()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .formLogin(f->f.disable()).httpBasic(b->b.disable())
   .authorizeHttpRequests(a->a.requestMatchers("/","/api/health","/api/users/login","/api/users/register","/api/leave-types","/error").permitAll().anyRequest().authenticated())
   .exceptionHandling(e->e.authenticationEntryPoint((req,res,ex)->res.sendError(401,"Authentication required")))
   .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class).build();
 }
}
