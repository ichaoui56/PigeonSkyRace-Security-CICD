package org.security.pigeonskyracesecuritycicd.dto.pigeon;


public record ResponsePigeonDTO(
        Long id,
        String ringNumber,
        String gender,
        int age,
        String color,
        Long userId
) {
}
