package org.security.pigeonskyracesecuritycicd.dto.competition;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompetitionDTO{

        private String name;

        private double latitude;

        private double longitude;

        private LocalDateTime departureTime;

        private int pigeonCount;

        private int percentage;

        String releasePlace;

}
