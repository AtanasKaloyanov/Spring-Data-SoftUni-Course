package com.example.modelmapping.entities;

import com.example.modelmapping.entities.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    private LocalDate birthday;

    private Address address;

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }
}
