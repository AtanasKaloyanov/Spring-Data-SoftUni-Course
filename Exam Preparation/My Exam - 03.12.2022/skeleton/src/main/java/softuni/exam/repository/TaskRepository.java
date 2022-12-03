package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Task;
import softuni.exam.models.entity.enums.CarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task AS t WHERE t.car.carType = :carType ORDER BY t.price DESC")
    Optional<List<Task>> findOnlyTasksWithCarTypeCoupeOrderedByPriceDesc(CarType carType);

    // •	Filter only coupe cars and order them by the price in descending order.
}
