package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    //If the apartment with the same town name and area already exists in the DB return "Invalid apartment".
    @Query("SELECT a FROM Apartment AS a WHERE a.town.townName = :townName AND a.area = :area")
    Apartment findApartmentByTownNameAndArea(String townName, Double area);

    Optional<Apartment> findApartmentById(Long id);
}
