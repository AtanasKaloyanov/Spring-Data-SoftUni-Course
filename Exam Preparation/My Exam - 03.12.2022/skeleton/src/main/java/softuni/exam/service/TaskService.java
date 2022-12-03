package softuni.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Task;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface TaskService{

    boolean areImported();

    String readTasksFileContent() throws IOException;

    String importTasks() throws IOException, JAXBException;

    String getCoupeCarTasksOrderByPrice();
}
