package com.example.football.repository;

import com.example.football.models.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {

    @Query("SELECT s FROM Stat AS s " +
            "WHERE s.shooting = :shooting AND s.passing = :passing AND s.endurance = :endurance")
    Optional<Stat> findStatByShootingPassingAndEndurance(Float shooting, Float passing, Float endurance);

    Optional<Stat> findById(Long id);
}
