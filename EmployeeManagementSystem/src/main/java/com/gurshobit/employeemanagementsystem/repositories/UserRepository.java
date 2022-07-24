package com.gurshobit.employeemanagementsystem.repositories;

import com.gurshobit.employeemanagementsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByUsername(String username);
}
