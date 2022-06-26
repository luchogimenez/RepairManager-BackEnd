package com.luchoDev.repairmanager.security.controller;

import com.luchoDev.repairmanager.security.dto.JwtDto;
import com.luchoDev.repairmanager.security.dto.LoginUser;
import com.luchoDev.repairmanager.security.dto.NewUser;
import com.luchoDev.repairmanager.security.entity.Role;
import com.luchoDev.repairmanager.security.entity.UserDTO;
import com.luchoDev.repairmanager.security.jwt.JwtProvider;
import com.luchoDev.repairmanager.security.role.RoleName;
import com.luchoDev.repairmanager.security.service.RoleService;
import com.luchoDev.repairmanager.security.service.UserService;
import com.luchoDev.repairmanager.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create-user")
    public ResponseEntity<?> newUer(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Error en algun campo"), HttpStatus.BAD_REQUEST);
        if(userService.existByUsername(newUser.getUsername()))
            return new ResponseEntity(new Message("ya existe ese nombre de usuarion"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("ya existe ese email"), HttpStatus.BAD_REQUEST);
        UserDTO userDTO = new UserDTO(newUser.getName(), newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        userDTO.setRoles(roles);
        userService.save(userDTO);
        return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser,BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("error login"),HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity(jwtDto,HttpStatus.OK);
    }
}
