package com.gurshobit.employeemanagementsystem.repositories;


import com.gurshobit.employeemanagementsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository()
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);
}
