package com.example.modelmapping.repositories;

import com.example.modelmapping.models.dto.EmployeeNamesDTO;
import com.example.modelmapping.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.example.modelmapping.models.dto.EmployeeNamesDTO (e.firstName, e.lastName) " +
            "FROM Employee AS e " +
            "WHERE e.id = :id")

    EmployeeNamesDTO findNamesById(Long id);
}
