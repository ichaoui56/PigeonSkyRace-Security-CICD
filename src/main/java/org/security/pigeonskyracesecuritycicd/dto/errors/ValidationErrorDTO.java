package org.security.pigeonskyracesecuritycicd.dto.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
public class ValidationErrorDTO {
    private int status;
    private Date timestamp;
    private String message;
    private Map<String, String> details;
}
