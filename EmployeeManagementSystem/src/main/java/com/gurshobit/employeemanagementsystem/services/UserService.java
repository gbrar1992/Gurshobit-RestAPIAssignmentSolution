package com.gurshobit.employeemanagementsystem.services;

import com.gurshobit.employeemanagementsystem.entities.Role;
import com.gurshobit.employeemanagementsystem.entities.User;
import com.gurshobit.employeemanagementsystem.repositories.RoleRepository;
import com.gurshobit.employeemanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.getByUsername(username);
    }

    public Optional<User> getById(int id){
        return userRepository.findById(id);
    }

    public Optional<User> assignUserRole(Integer userId, Integer roleId){
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        if(user != null && role != null){
            Set<Role> userRoles = user.getRoles();
            userRoles.add(role);
            user.setRoles(userRoles);

            userRepository.save(user);
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> unAssignUserRole(Integer userId, Integer roleId){
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        if(user != null && role != null){
            Set<Role> userRoles = user.getRoles();
            if(userRoles.size() > 0){
                userRoles.removeIf( roleData -> roleData.getId() == roleId);
            }
            user.setRoles(userRoles);

            userRepository.save(user);
        }

        return Optional.ofNullable(user);
    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }
}
