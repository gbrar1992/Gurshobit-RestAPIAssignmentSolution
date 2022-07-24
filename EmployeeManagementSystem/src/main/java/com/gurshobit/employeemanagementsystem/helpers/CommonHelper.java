package com.gurshobit.employeemanagementsystem.helpers;

import com.gurshobit.employeemanagementsystem.entities.Employee;
import com.gurshobit.employeemanagementsystem.entities.Role;
import com.gurshobit.employeemanagementsystem.entities.User;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;

public class CommonHelper {
    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public HashMap<String, Object> getEmployeesHashMapOutput(List<Employee> objectList, String moduleName) {
        HashMap<String, Object> responseMap = new HashMap<>();

        HashMap<String, String> info = new HashMap<>();

        boolean status = false;

        String message = "No " + moduleName + "s Found";
        if (objectList.size() > 0) {
            message = "List of all " + moduleName + "s";
            status = true;
        }

        info.put("message", message);

        responseMap.put("success", status);
        responseMap.put("info", info);
        responseMap.put("data", objectList);
        return responseMap;
    }

    public HashMap<String, Object> getUsersHashMapOutput(List<User> objectList, String moduleName) {
        HashMap<String, Object> responseMap = new HashMap<>();

        HashMap<String, String> info = new HashMap<>();

        boolean status = false;


        String message = "No " + moduleName + "s Found";
        if (objectList.size() > 0) {
            message = "List of all " + moduleName + "s";
            status = true;
        }

        info.put("message", message);

        responseMap.put("success", status);
        responseMap.put("info", info);
        responseMap.put("data", objectList);
        return responseMap;
    }

    public HashMap<String, Object> getRolesHashMapOutput(List<Role> objectList, String moduleName) {
        HashMap<String, Object> responseMap = new HashMap<>();

        HashMap<String, String> info = new HashMap<>();

        boolean status = false;

        String message = "No " + moduleName + "s Found";
        if (objectList.size() > 0) {
            message = "List of all " + moduleName + "s";
            status = true;
        }

        info.put("message", message);

        responseMap.put("success", status);
        responseMap.put("info", info);
        responseMap.put("data", objectList);
        return responseMap;
    }



}

