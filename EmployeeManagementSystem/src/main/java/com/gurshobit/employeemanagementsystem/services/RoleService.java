package com.gurshobit.employeemanagementsystem.services;

import com.gurshobit.employeemanagementsystem.entities.Role;
import com.gurshobit.employeemanagementsystem.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("roleService")
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleByName(String name){
        return roleRepository.getByName(name);
    }

    public Optional<Role> findById(Integer id){
        return roleRepository.findById(id);

    }

}
