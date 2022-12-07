package com.example.nextleveltechnologies.repositories;

import com.example.nextleveltechnologies.models.companies.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company ,Long> {
}
