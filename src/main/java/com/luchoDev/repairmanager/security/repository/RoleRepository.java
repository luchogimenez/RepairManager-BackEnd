package com.luchoDev.repairmanager.security.repository;

import com.luchoDev.repairmanager.security.entity.Role;
import com.luchoDev.repairmanager.security.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
