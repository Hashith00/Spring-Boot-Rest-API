package com.managment.employeeManagment.service.impl;

import com.managment.employeeManagment.dto.EmployeeDto;
import com.managment.employeeManagment.entity.Employee;
import com.managment.employeeManagment.exception.ResourceNotFoundException;
import com.managment.employeeManagment.mapper.EmployeeMapper;
import com.managment.employeeManagment.repository.EmployeeRepository;
import com.managment.employeeManagment.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeDto> employeeDtos = new ArrayList<>();
//        for (Employee employee: employees) {
//            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(employee));
//        }
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDeatils) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not Found " + employeeId));

        employee.setFirstname(updatedEmployeeDeatils.getFirstName());
        employee.setLastname(updatedEmployeeDeatils.getLastName());
        employee.setEmail(updatedEmployeeDeatils.getEmail());
        Employee updatedEmployee =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not Found " + employeeId));
        employeeRepository.deleteById(employeeId);

    }
}
