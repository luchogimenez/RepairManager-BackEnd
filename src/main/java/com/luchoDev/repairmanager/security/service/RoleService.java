package com.luchoDev.repairmanager.security.service;

import com.luchoDev.repairmanager.security.entity.Role;
import com.luchoDev.repairmanager.security.repository.RoleRepository;
import com.luchoDev.repairmanager.security.role.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public void save(Role roleUser) {
        roleRepository.save(roleUser);
    }
}
