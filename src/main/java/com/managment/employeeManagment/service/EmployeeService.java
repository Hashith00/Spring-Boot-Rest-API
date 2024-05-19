package com.managment.employeeManagment.service;

import com.managment.employeeManagment.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployeeDto(EmployeeDto employeeDto);
    EmployeeDto getEmployeeBuId(Long employeeId);
}
