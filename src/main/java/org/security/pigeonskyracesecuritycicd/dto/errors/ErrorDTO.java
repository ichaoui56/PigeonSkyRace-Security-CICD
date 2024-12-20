package org.security.pigeonskyracesecuritycicd.dto.errors;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int status;
    private Date timestamp;
    private String message;
}
