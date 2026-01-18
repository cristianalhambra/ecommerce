package com.tienda.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita la protección CSRF (necesario para APIs REST simples)
                .csrf(AbstractHttpConfigurer::disable)

                // Configura la autorización de peticiones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", // Permite acceso a la raíz
                                "/error", // Permite acceso a la página de error
                                "/api/**", // Permite acceso a endpoints públicos
                                "/api/auth/**", // Permite acceso a endpoints de autenticación (login, register)
                                "/api/auth/v1/auth/**", // Permite acceso a endpoints de autenticación versión 1
                                "/api/users/**", // Permite acceso a endpoints de usuarios
                                "/api/products/**", // Permite acceso a endpoints de productos
                                "/api/swagger-ui/**", // Permite acceso a Swagger UI
                                "/api/v3/api-docs/**" // Permite acceso a API docs
                        ).permitAll()
                        // Permitir peticiones OPTIONS (CORS preflight) explícitamente si fuera necesario
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        // Cualquier otra petición (como login) debe ser autenticada
                        .anyRequest().permitAll()
                )
                // Habilita el CORS para que Angular pueda comunicarse (puerto 4200)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    // Configuramos el CORS a nivel de Spring Security (complementa al @CrossOrigin)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Angular se está ejecutando en 4200
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Cabeceras permitidas
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin"));
        // Permitir que el navegador exponga las cabeceras de respuesta
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
