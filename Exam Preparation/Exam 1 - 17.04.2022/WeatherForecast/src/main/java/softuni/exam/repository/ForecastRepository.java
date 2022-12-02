package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.util.List;


@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast AS f WHERE f.city = :city AND f.dayOfWeek = :dayOfWeek")
    Forecast findForeCastWithTownAndDayOfWeek(City city, DayOfWeek dayOfWeek);

    @Query("SELECT f FROM Forecast As f WHERE f.dayOfWeek =:dayOfWeek AND f.city.population <:population " +
    "ORDER BY f.maxTemperature DESC, f.id")
    List<Forecast> findAllByDayOfWeekAndCity(DayOfWeek dayOfWeek, Long population);
}
