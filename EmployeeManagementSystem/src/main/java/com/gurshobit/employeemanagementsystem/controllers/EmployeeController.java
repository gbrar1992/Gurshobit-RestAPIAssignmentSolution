package com.gurshobit.employeemanagementsystem.controllers;

import com.gurshobit.employeemanagementsystem.entities.Employee;
import com.gurshobit.employeemanagementsystem.helpers.CommonHelper;
import com.gurshobit.employeemanagementsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final CommonHelper commonHelper = new CommonHelper();

    @PostMapping("")
    public String home() {
        return "Welcome to Employee Management System";
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> list() {

        List<Employee> employeeList = employeeService.findAll();

        return commonHelper.getEmployeesHashMapOutput(employeeList, "employee");
    }

    @PostMapping(value ="/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object> add(@RequestBody Employee employee) {
        HashMap<String, Object> responseMap = new HashMap<>();
        employeeService.save(employee);

        Optional<Employee> optionalEmployee = employeeService.findById(employee.getId());
        if(optionalEmployee.isPresent()){
            Employee savedEmployee = optionalEmployee.get();
            responseMap.put("success", true);

            HashMap<String,String> info = new HashMap<>();

            info.put("message","Employee Saved Successfully!");
            responseMap.put("info", info);
            responseMap.put("data", employee);

        } else {
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();
            info.put("message",String.format("Employee not created!"));
            responseMap.put("info", info);

        }
        return responseMap;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    @ResponseBody
    public HashMap<String,Object> update(@RequestBody Employee employee, @PathVariable int id) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(id > 0) {
            Optional<Employee> optionalEmployee = employeeService.findById(id);
            if(optionalEmployee.isPresent()){
                Employee oldEmployee = optionalEmployee.get();

                if(employee.getId() == oldEmployee.getId()){

                    responseMap.put("success", true);

                    HashMap<String,String> info = new HashMap<>();

                    info.put("message",String.format("Updated employee with id: %d", id));
                    responseMap.put("info", info);
                    responseMap.put("data", employee);

                    employeeService.save(employee);
                } else {
                    responseMap.put("success", false);

                    HashMap<String,String> info = new HashMap<>();

                    info.put("message",String.format("Employee ID doesn't match!"));

                    responseMap.put("info", info);

                }

            } else {
                responseMap.put("success", false);

                HashMap<String,String> info = new HashMap<>();

                info.put("message",String.format("Employee doesn't exists!"));

                responseMap.put("info", info);

            }

        } else{
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();

            info.put("message",String.format("Invalid Employee ID!!"));

            responseMap.put("info", info);

        }
        return responseMap;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object > delete(@PathVariable int id) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(id > 0) {
            Optional<Employee> optionalEmployee = employeeService.findById(id);
            if(optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();

                if(employee.getId() == id){

                    responseMap.put("success", true);

                    HashMap<String,String> info = new HashMap<>();

                    info.put("message",String.format("Deleted employee with id: %d", id));
                    responseMap.put("info", info);
                    responseMap.put("data", employee);

                    employeeService.deleteById(id);
                } else {
                    responseMap.put("success", false);

                    HashMap<String,String> info = new HashMap<>();

                    info.put("message",String.format("Employee ID doesn't match!"));

                    responseMap.put("info", info);

                }

            } else {
                responseMap.put("success", false);

                HashMap<String,String> info = new HashMap<>();

                info.put("message",String.format("Employee doesn't exists!"));

                responseMap.put("info", info);

            }

        } else{
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();

            info.put("message",String.format("Invalid Employee ID!!"));

            responseMap.put("info", info);

        }
        return responseMap;


    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public HashMap<String, Object> getById(@PathVariable int id) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(id > 0) {
            Optional<Employee> optionalEmployee = employeeService.findById(id);
            if(optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();

                responseMap.put("success", true);

                HashMap<String,String> info = new HashMap<>();

                info.put("message",String.format("Employee Details with id: %d", id));
                responseMap.put("info", info);
                responseMap.put("data", employee);

            } else {
                responseMap.put("success", false);

                HashMap<String,String> info = new HashMap<>();

                info.put("message",String.format("Employee doesn't exists!"));

                responseMap.put("info", info);

            }

        } else{
            responseMap.put("success", false);

            HashMap<String,String> info = new HashMap<>();

            info.put("message",String.format("Invalid Employee ID!!"));

            responseMap.put("info", info);

        }
        return responseMap;
    }

    @GetMapping("/search/{firstname}")
    public HashMap<String, Object> search(@PathVariable String firstname) {
        List<Employee> employeeList = employeeService.search(firstname);

        return commonHelper.getEmployeesHashMapOutput(employeeList,"employee");
    }

    @GetMapping("/sort")
    public HashMap<String, Object> getSortedEmployeeList(@RequestParam String direction) {
        List<Employee> employeeList = employeeService.findAllSortedEmployees(commonHelper.getSortDirection(direction));

        return commonHelper.getEmployeesHashMapOutput(employeeList,"employee");
    }



}
