package com.example.modelmapping;

import com.example.modelmapping.dto.EmployeeDTO;
import com.example.modelmapping.entities.Address;
import com.example.modelmapping.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Main implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Task 1

        Address address = new Address("here");
        Employee employee = new Employee("First", "Last", BigDecimal.valueOf(1000000), LocalDate.now(), address);

        ModelMapper modelMapper = new ModelMapper();
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        System.out.println(employeeDTO);
    }
}
