package com.example.modelmapping;

import com.example.modelmapping.models.dto.EmployeeDTO;
import com.example.modelmapping.models.entities.Address;
import com.example.modelmapping.models.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ModelMapperMain implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Task 1

//        Address address = new Address("here", "the dream country");
//        Employee employee = new Employee("First", "Last", BigDecimal.valueOf(1000000), LocalDate.now(), address);
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        TypeMap<Employee, EmployeeDTO> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeDTO.class);
//
//        typeMap.addMappings(mapping -> mapping.map(source -> source.getAddress().getCity(),
//                EmployeeDTO::setCity));
//
//        typeMap.validate();
//
//        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
//
//        System.out.println(employeeDTO);

    }
}
