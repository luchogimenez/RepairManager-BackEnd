package com.luchoDev.repairmanager.util;

import com.luchoDev.repairmanager.security.entity.Role;
import com.luchoDev.repairmanager.security.role.RoleName;
import com.luchoDev.repairmanager.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleCreate implements CommandLineRunner {

    @Autowired
    RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);

        roleService.save(roleAdmin);
        roleService.save(roleUser);
    }
}/*
public class RoleCreate{

}*/
