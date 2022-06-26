package com.luchoDev.repairmanager.security.service;

import com.luchoDev.repairmanager.security.entity.UserDTO;
import com.luchoDev.repairmanager.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<UserDTO> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(UserDTO userDTO){
        userRepository.save(userDTO);
    }
}
