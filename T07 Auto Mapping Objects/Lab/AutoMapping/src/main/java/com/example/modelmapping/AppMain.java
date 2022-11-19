package com.example.modelmapping;

import com.example.modelmapping.models.dto.AddressDTO;
import com.example.modelmapping.models.dto.CreateEmployeeDTO;
import com.example.modelmapping.models.entities.Address;
import com.example.modelmapping.models.entities.Employee;
import com.example.modelmapping.services.AddressService;
import com.example.modelmapping.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {
    private Scanner scanner;
    private AddressService addressService;
    private EmployeeService employeeService;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService) {
        this.scanner = new Scanner(System.in);

        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

      // createAddress(scanner);
    // createEmployee(scanner);
     //   printAllEmployes();
        printEmployeeNames();


    }

    private void printEmployeeNames() {
        System.out.println(this.employeeService.findNamesById(Long.parseLong(scanner.nextLine())));
    }

    private void printAllEmployes() {
        this.employeeService.findAll()
                .forEach(System.out::println);

    }

    private void createEmployee(Scanner scanner) {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

      //  long addressId = Long.parseLong(scanner.nextLine());

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        AddressDTO address = new AddressDTO(city, country);

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.createEmployee(employeeDTO);

        System.out.println(employee);
    }

    // Ivan
    // 100 000
    //

    private void createAddress(Scanner scanner) {

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        AddressDTO data = new AddressDTO(city, country);

        Address address = addressService.create(data);

        System.out.println(address);
    }
}

