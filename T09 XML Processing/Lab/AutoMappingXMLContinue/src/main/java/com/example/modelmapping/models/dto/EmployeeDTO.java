package com.example.modelmapping.models.dto;


public class EmployeeDTO {

    private String firstName;

    private String lastName;

    private String addressCity;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Override
    public String toString() {
        return "The address of " + this.firstName + " " + this.lastName + " is " + this.addressCity;
    }
}
