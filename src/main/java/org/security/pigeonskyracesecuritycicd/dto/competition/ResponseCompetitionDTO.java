package org.security.pigeonskyracesecuritycicd.dto.competition;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.pigeonskyracesecuritycicd.model.entity.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompetitionDTO {
    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private LocalDateTime departureTime;

    private int pigeonCount;

    private int percentage;

    private String releasePlace;
}
