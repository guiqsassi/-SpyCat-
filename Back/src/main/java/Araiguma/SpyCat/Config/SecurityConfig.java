package Araiguma.SpyCat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import Araiguma.SpyCat.Services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SecurityFilter securityFilter;
    
    @Autowired
    private LoginEntryPoint loginEntryPoint;


    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userService).passwordEncoder(encoder());
        
        var authenticationManager = builder.build();

        http.csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeHttpRequests(requests -> 
                    requests
                        .requestMatchers(HttpMethod.POST, "/ongs").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/savePassword").permitAll()
                        .requestMatchers(HttpMethod.POST, "/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/refresh").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pets/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pets").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ongs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handling -> handling
                    .authenticationEntryPoint(loginEntryPoint)
                    .accessDeniedHandler(new AccessDeniedHandlerImpl())
                );

        return http.build();
    }

     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // ou especifique a origem permitida
        configuration.addAllowedMethod("*"); // ou especifique métodos permitidos
        configuration.addAllowedHeader("*"); // ou especifique cabeçalhos permitidos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
