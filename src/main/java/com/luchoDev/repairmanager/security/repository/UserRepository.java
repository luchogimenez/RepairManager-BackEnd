package com.luchoDev.repairmanager.security.repository;

import com.luchoDev.repairmanager.security.entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO,Long> {
    Optional<UserDTO> findByUsername(String username);
    boolean existsByUsername (String username);
    boolean existsByEmail(String email);

}
