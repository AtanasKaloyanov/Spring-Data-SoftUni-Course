package softuni.exam.service;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Car;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface CarService  {

    boolean areImported();

    String readCarsFromFile() throws IOException;

    String importCars() throws IOException, JAXBException;
}
