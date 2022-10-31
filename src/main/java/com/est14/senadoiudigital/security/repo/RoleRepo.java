package com.est14.senadoiudigital.security.repo;


import com.est14.senadoiudigital.security.enums.NameRole;
import com.est14.senadoiudigital.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByNameRole(NameRole nameRole);
}
