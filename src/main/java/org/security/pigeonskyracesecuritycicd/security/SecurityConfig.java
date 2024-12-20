package org.security.pigeonskyracesecuritycicd.security;

import lombok.RequiredArgsConstructor;
import org.security.pigeonskyracesecuritycicd.exception.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider customAuthenticationProvider;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/register").permitAll();
                    authz.requestMatchers("/admin/**").hasRole("ADMIN");
                    authz.requestMatchers("/organizer/**").hasRole("ORGANIZER");
                    authz.anyRequest().authenticated();
                })
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            customAccessDeniedHandler.handle(request, response, (AccessDeniedException) accessDeniedException);
                        })
                )
                .authenticationProvider(customAuthenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
