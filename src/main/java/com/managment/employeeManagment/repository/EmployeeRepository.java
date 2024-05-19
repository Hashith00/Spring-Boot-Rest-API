package com.managment.employeeManagment.repository;

import com.managment.employeeManagment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
