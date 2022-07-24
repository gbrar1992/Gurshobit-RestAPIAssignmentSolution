package com.gurshobit.employeemanagementsystem.controllers;

import com.gurshobit.employeemanagementsystem.entities.User;
import com.gurshobit.employeemanagementsystem.helpers.CommonHelper;
import com.gurshobit.employeemanagementsystem.models.UserRoleData;
import com.gurshobit.employeemanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account/users")
public class UserController {
    @Autowired
    UserService userService;

    CommonHelper commonHelper = new CommonHelper();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> allUsers(){
        List<User> userList = userService.findAll();

        return commonHelper.getUsersHashMapOutput(userList, "user");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> addUser(@RequestBody @Valid User user){
        HashMap<String, Object> responseMap = new HashMap<>();
        user = userService.save(user);

        Optional<User> optionalUser = userService.getById(user.getId());
        if(optionalUser.isPresent()){
            User savedUser = optionalUser.get();
            responseMap.put("success", true);

            HashMap<String,String> info = new HashMap<>();

            info.put("message","User Saved Successfully");
            responseMap.put("info", info);
            responseMap.put("data", savedUser);

        } else {
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();
            info.put("message",String.format("User not created!"));
            responseMap.put("info", info);
            responseMap.put("data", user);

        }
        return responseMap;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> assignRole(@RequestBody @Valid UserRoleData userRoleData){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<User> optionalUser = userService.assignUserRole(userRoleData.getUserId(), userRoleData.getRoleId());

        if(optionalUser.isPresent()){
            User savedUser = optionalUser.get();
            responseMap.put("success", true);

            HashMap<String,String> info = new HashMap<>();

            info.put("message","Role assigned to user Successfully");
            responseMap.put("info", info);
            responseMap.put("data", savedUser);

        } else {
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();
            info.put("message","Role assigning Failed!");
            responseMap.put("info", info);

        }
        return responseMap;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> unAssignRole(@RequestBody @Valid UserRoleData userRoleData){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<User> optionalUser = userService.unAssignUserRole(userRoleData.getUserId(), userRoleData.getRoleId());

        if(optionalUser.isPresent()){
            User savedUser = optionalUser.get();
            responseMap.put("success", true);

            HashMap<String,String> info = new HashMap<>();

            info.put("message","Role un-assigned to user Successfully");
            responseMap.put("info", info);
            responseMap.put("data", savedUser);

        } else {
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();
            info.put("message","Role un-assigning Failed!");
            responseMap.put("info", info);

        }
        return responseMap;

    }

}
