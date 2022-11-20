package com.example.modelmapping.services;

import com.example.modelmapping.models.dto.CreateEmployeeDTO;
import com.example.modelmapping.models.dto.EmployeeDTO;
import com.example.modelmapping.models.dto.EmployeeNamesDTO;
import com.example.modelmapping.models.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(Long id);
}
