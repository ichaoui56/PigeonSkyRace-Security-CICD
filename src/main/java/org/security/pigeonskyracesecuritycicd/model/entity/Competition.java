package org.security.pigeonskyracesecuritycicd.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.security.pigeonskyracesecuritycicd.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Competition name cannot be blank")
    @Size(min = 3, max = 100, message = "Competition name must be between 3 and 100 characters")
    private String name;

    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be between -90.0 and 90.0")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be between -90.0 and 90.0")
    private double latitude;

    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be between -180.0 and 180.0")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be between -180.0 and 180.0")
    private double longitude;

    @NotNull(message = "Departure time cannot be null")
    private LocalDateTime departureTime;

    @Min(value = 1, message = "Pigeon count must be at least 1")
    private int pigeonCount;

    @Min(value = 0, message = "Percentage cannot be negative")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private int percentage;

    private boolean status;

    @NotBlank(message = "Release place cannot be blank")
    private String releasePlace;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pigeon> pigeons = new ArrayList<>();
}
