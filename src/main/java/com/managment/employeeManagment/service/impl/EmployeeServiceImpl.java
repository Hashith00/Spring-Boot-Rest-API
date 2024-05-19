package com.managment.employeeManagment.service.impl;

import com.managment.employeeManagment.dto.EmployeeDto;
import com.managment.employeeManagment.entity.Employee;
import com.managment.employeeManagment.exception.ResourceNotFoundException;
import com.managment.employeeManagment.mapper.EmployeeMapper;
import com.managment.employeeManagment.repository.EmployeeRepository;
import com.managment.employeeManagment.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


// Dependency injection has done using constructor
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployeeDto(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeBuId(Long employeeId) {
        Employee employee=  employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee is not Found " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
