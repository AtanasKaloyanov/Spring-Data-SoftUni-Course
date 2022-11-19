package com.example.modelmapping.dto;

import java.math.BigDecimal;

public class EmployeeDTO {

    private String firstName;

    private String lastName;

    private BigDecimal money;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + money +
                '}';
    }
}
