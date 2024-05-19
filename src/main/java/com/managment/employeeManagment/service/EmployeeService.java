package com.managment.employeeManagment.service;

import com.managment.employeeManagment.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployeeDto(EmployeeDto employeeDto);
    EmployeeDto getEmployeeBuId(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDeatils);
    void deleteEmployee(Long employeeId);
}
