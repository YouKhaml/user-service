package org.example.userservice.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

//
//    private JwtAutenticationFilter jwtAuthenticationFilter;
//
//
//    private UserDetailsService userDetailsService;
//
//    public SecurityConfig(JwtAutenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // désactive CSRF pour les requêtes POST sans session
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT = stateless
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll() // endpoints publics
//                        .anyRequest().authenticated() // tout le reste nécessite un token
//                )
//                .authenticationProvider(authenticationProvider()) // configuration du provider
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // injecter notre filtre JWT
//                .build();
//    }
//    // 2. Provider d'authentification basé sur notre UserDetailsService
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//    // 3. Gestionnaire d’authentification (utilisé dans AuthController)
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

    // 4. Encodeur de mots de passe
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
