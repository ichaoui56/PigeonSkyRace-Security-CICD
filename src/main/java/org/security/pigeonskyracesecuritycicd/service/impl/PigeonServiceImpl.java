package org.security.pigeonskyracesecuritycicd.service.impl;

import lombok.AllArgsConstructor;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.RequestPigeonDTO;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.ResponsePigeonDTO;
import org.security.pigeonskyracesecuritycicd.exception.AlreadyExistsException;
import org.security.pigeonskyracesecuritycicd.exception.NotFoundException;
import org.security.pigeonskyracesecuritycicd.mapper.PigeonMapper;
import org.security.pigeonskyracesecuritycicd.model.RoleType;
import org.security.pigeonskyracesecuritycicd.model.entity.Pigeon;
import org.security.pigeonskyracesecuritycicd.model.entity.User;
import org.security.pigeonskyracesecuritycicd.repository.PigeonRepository;
import org.security.pigeonskyracesecuritycicd.repository.UserRepository;
import org.security.pigeonskyracesecuritycicd.service.PigeonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PigeonServiceImpl implements PigeonService {

    private final PigeonRepository pigeonRepository;
    private final UserRepository userRepository;
    private final PigeonMapper pigeonMapper;

    @Transactional
    @Override
    public String addPigeon(RequestPigeonDTO requestPigeonDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        User user = userRepository.findByUsername(currentUser)
                .orElseThrow(() -> new NotFoundException("User with username '" + currentUser + "' not found."));

        if (!user.getRole().getRoleType().equals(RoleType.ROLE_USER)) {
            throw new IllegalStateException("Only users with the USER role can create pigeons");
        }

        pigeonRepository.findByRingNumber(requestPigeonDTO.ringNumber())
                .ifPresent(pigeon -> {
                    throw new AlreadyExistsException(
                            "Pigeon with ring number '" + requestPigeonDTO.ringNumber() + "' already exists.");
                });

        Pigeon pigeon = new Pigeon();
        pigeon.setRingNumber(requestPigeonDTO.ringNumber());
        pigeon.setGender(requestPigeonDTO.gender());
        pigeon.setAge(requestPigeonDTO.age());
        pigeon.setColor(requestPigeonDTO.color());
        pigeon.setUser(user);

        Pigeon savedPigeon = pigeonRepository.save(pigeon);

        return "Pigeon with ring number '" + requestPigeonDTO.ringNumber() + "' added successfully.";
    }
}