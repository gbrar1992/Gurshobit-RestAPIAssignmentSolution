package com.gurshobit.employeemanagementsystem.controllers;

import com.gurshobit.employeemanagementsystem.entities.Role;
import com.gurshobit.employeemanagementsystem.helpers.CommonHelper;
import com.gurshobit.employeemanagementsystem.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    
    CommonHelper commonHelper = new CommonHelper();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> allRoles(){
        List<Role> roleList = roleService.findAll();

        return commonHelper.getRolesHashMapOutput(roleList, "role");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> addRole(@RequestBody @Valid Role role){
        HashMap<String, Object> responseMap = new HashMap<>();

        String roleName = role.getName().toUpperCase();
        if(!roleName.isEmpty()){
            role.setName("ROLE_" + roleName.replaceAll("ROLE_",""));
            roleService.save(role);
        }

        Optional<Role> optionalRole = roleService.getRoleByName(role.getName());
        if(optionalRole.isPresent()){
            Role savedRole = optionalRole.get();
            responseMap.put("success", true);

            HashMap<String,String> info = new HashMap<>();

            info.put("message","Role Saved Successfully");
            responseMap.put("info", info);
            responseMap.put("data", savedRole);

        } else {
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();
            info.put("message","Role not created!");
            responseMap.put("info", info);

        }
        return responseMap;

    }
}
