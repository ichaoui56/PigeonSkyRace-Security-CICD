package org.security.pigeonskyracesecuritycicd.service;

import org.security.pigeonskyracesecuritycicd.dto.pigeon.RequestPigeonDTO;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.ResponsePigeonDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public interface PigeonService {
    String addPigeon(RequestPigeonDTO requestPigeonDTO);
}
