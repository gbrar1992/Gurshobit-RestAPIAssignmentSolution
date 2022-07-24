package com.gurshobit.employeemanagementsystem.repositories;

import com.gurshobit.employeemanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> getByName(String name);
}
