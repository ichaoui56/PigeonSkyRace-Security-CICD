package org.security.pigeonskyracesecuritycicd.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.security.pigeonskyracesecuritycicd.dto.competition.RequestCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.competition.ResponseCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.RequestPigeonDTO;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.ResponsePigeonDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.User;
import org.security.pigeonskyracesecuritycicd.service.CompetitionService;
import org.security.pigeonskyracesecuritycicd.service.PigeonService;
import org.security.pigeonskyracesecuritycicd.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/breeder")
@AllArgsConstructor
public class PigeonController {

    private final PigeonService pigeonService;

    @PostMapping("/addPigeon")
    public ResponseEntity<String> addPigeon(@Valid @RequestBody RequestPigeonDTO requestPigeonDTO, Principal principal){
        String response = pigeonService.addPigeon(requestPigeonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


