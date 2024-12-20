package org.security.pigeonskyracesecuritycicd;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

// This is sufficient
@SpringBootApplication
public class PigeonSkyRaceSecurityCicdApplication {
    public static void main(String[] args) {
        SpringApplication.run(PigeonSkyRaceSecurityCicdApplication.class, args);
    }
}

