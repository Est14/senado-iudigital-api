package com.est14.senadoiudigital.security.service;

import com.est14.senadoiudigital.security.enums.NameRole;
import com.est14.senadoiudigital.security.model.Role;
import com.est14.senadoiudigital.security.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Optional<Role> getRole(NameRole nameRole){
        return roleRepo.findByNameRole(nameRole);
    }
}
