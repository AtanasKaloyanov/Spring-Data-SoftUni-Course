package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.task.TaskImportDTO;
import softuni.exam.models.dto.task.TaskImportWrapperDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.models.entity.enums.CarType;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.TASKS_IMPORT_PATH;

@Service
public class TaskServiceImpl implements TaskService {
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final TaskRepository taskrepository;

    private final CarRepository carRepository;

    private final MechanicRepository mechanicRepository;

    private final PartRepository partRepository;

    @Autowired
    public TaskServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, TaskRepository taskrepository, CarRepository carRepository, MechanicRepository mechanicRepository, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.taskrepository = taskrepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;
        this.partRepository = partRepository;
    }

    @Override
    public boolean areImported() {
        return this.taskrepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_IMPORT_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        TaskImportWrapperDTO importWrapperDTO = this.xmlParser.fromFile(TASKS_IMPORT_PATH, TaskImportWrapperDTO.class);
        List<TaskImportDTO> importDTOs = importWrapperDTO.getTaskImportDTOList();

        for (TaskImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String mechanicName = importDTO.getMechanic().getFirstName();
            if (this.mechanicRepository.findByFirstName(mechanicName).isEmpty()) {
                isValid = false;
            }

            Long carId = importDTO.getCar().getId();
            if (this.carRepository.findById(carId).isEmpty()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_TASKS_FORMAT, importDTO.getPrice()));
                Car car = this.carRepository.findById(carId).get();
                Mechanic mechanic = this.mechanicRepository.findByFirstName(mechanicName).get();

                Long portId = importDTO.getPart().getId();
                Part part = this.partRepository.findById(portId).get();

                Task task = this.modelMapper.map(importDTO, Task.class);
                task.setCar(car);
                task.setMechanic(mechanic);
                task.setPart(part);

                this.taskrepository.saveAndFlush(task);
            } else {
                sb.append(String.format(INVALID_TASK));
            }

        }



            // •If the given mechanic name doesn’t already exist in the DB return "Invalid task".
            //•	The provided part ids will always be valid.
            //•	Format the price to the second digit after the decimal point.
        return sb.toString();
        }



    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        CarType carType = CarType.coupe;
        List<Task> tasksForPrinting = this.taskrepository.findOnlyTasksWithCarTypeCoupeOrderedByPriceDesc(carType).get();

        for (Task task : tasksForPrinting) {
            sb.append(String.format(CAR_PRINT_RESULT, task.getCar().getCarMake(), task.getCar().getCarModel(), task.getCar().getKilometers()));
            sb.append(String.format(MECHANIC_PRINT_RESULT, task.getMechanic().getFirstName(), task.getMechanic().getLastName(), task.getId()));
            sb.append(String.format(ENGINE_PRINT_RESULT, task.getCar().getEngine()));
            sb.append(String.format(String.format(PRICE_PRINT_RESULT, task.getPrice())));
        }

        //  // •	"Car {carMake} {carModel} with {kilometers}km
        //    //                                    -Mechanic: {firstName} {lastName} - task №{taskId}:¬¬
        //    //                                    --Engine: {engine}
        //    //                                    ---Price: {taskPrice}$

        //  public static final String CAR_PRINT_RESULT = "Car %s %s with %dkm%n";
        //    public static final String MECHANIC_PRINT_RESULT = "\t-Mechanic: %s %s - task №%d:%n";
        //    public static final String ENGINE_PRINT_RESULT = "\t--Engine: %.2f%n";
        //    public static final String PRICE_PRINT_RESULT = "\t---Price: %.2f$%n";

        return sb.toString();
    }
}
