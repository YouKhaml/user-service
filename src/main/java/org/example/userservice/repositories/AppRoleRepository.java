package org.example.userservice.repositories;

import org.example.userservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    boolean existsByRoleName(String role);
    AppRole findByRoleName(String role);
}
