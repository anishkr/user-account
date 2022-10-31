package com.test.accounts.repository;

import com.test.accounts.entity.Role;
import com.test.accounts.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
