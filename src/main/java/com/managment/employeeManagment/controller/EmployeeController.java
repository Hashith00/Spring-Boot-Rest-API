package com.managment.employeeManagment.controller;

import com.managment.employeeManagment.dto.EmployeeDto;
import com.managment.employeeManagment.entity.Employee;
import com.managment.employeeManagment.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    // Build add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody  EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployeeDto(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto wantedEmployeeDto = employeeService.getEmployeeBuId(employeeId);
        return new ResponseEntity<>(wantedEmployeeDto, HttpStatus.OK);
    }

}
