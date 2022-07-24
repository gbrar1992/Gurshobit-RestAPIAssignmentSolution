package com.gurshobit.employeemanagementsystem.services;

import com.gurshobit.employeemanagementsystem.entities.Employee;
import com.gurshobit.employeemanagementsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    public void save(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    public List<Employee> search(String firstName) {
        return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
    }

    public List<Employee> findAllSortedEmployees(Sort.Direction direction) {
        return employeeRepository.findAll(Sort.by(direction, "firstName"));
    }
}
