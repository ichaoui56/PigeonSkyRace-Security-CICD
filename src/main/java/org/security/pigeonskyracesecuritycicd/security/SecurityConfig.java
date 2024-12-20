package org.security.pigeonskyracesecuritycicd.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/register", "/login", "/oauth2/authorization/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/user/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/organizer/**").hasAuthority("ROLE_ORGANIZER")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        http.cors(customizer -> customizer.configurationSource(corsConfigurationSource));
        http.csrf(customizer -> customizer.disable());

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var claims = jwt.getClaims();
            Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
            List<String> roles = (List<String>) realmAccess.get("roles");
            System.out.println("the roles " + roles);
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role)) // Ensure the "ROLE_" prefix is added
                    .collect(Collectors.toList());
        });
        return converter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/PSRauth/protocol/openid-connect/certs").build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://localhost:8443");
        configuration.addAllowedHeader("Authorization, Origin, Content-Type, Accept, Cache-Control");
        configuration.addAllowedMethod("HEAD, GET, PUT, POST, OPTIONS");
        configuration.addExposedHeader("Authorization, Cache-Control");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
