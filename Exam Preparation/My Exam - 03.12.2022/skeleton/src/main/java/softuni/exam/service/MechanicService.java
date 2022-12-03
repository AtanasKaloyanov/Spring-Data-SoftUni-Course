package softuni.exam.service;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Mechanic;

import java.io.IOException;

// TODO: Implement all methods
public interface MechanicService {

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;
}
