package org.security.pigeonskyracesecuritycicd.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.security.pigeonskyracesecuritycicd.dto.errors.ErrorDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        ErrorDTO errorMessageDTO = ErrorDTO.builder()
                .message("Authentication failed: Invalid email or password.")
                .timestamp(new Date())
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .build();

        String jsonResponse = objectMapper.writeValueAsString(errorMessageDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    // public void handleAccessDeniedException(HttpServletRequest request,
    // HttpServletResponse response, AccessDeniedException ex) throws IOException {
    //
    // ErrorDTO errorMessageDTO = ErrorDTO.builder()
    // .message("Access denied: You do not have permission to access this
    // resource.")
    // .status(HttpServletResponse.SC_FORBIDDEN)
    // .build();
    //
    // String jsonResponse = objectMapper.writeValueAsString(errorMessageDTO);
    //
    // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    // response.setContentType("application/json");
    // response.getWriter().write(jsonResponse);
    // }
}
