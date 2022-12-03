package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.car.CarImportDTO;
import softuni.exam.models.dto.car.CarImportWrapperDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_CAR;
import static softuni.exam.constants.Messages.VALID_CAR_FORMAT;
import static softuni.exam.constants.Paths.CARS_IMPORT_PATH;

@Service
public class CarServiceImpl implements CarService {
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, CarRepository carRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_IMPORT_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        CarImportWrapperDTO importWrapperDTO = this.xmlParser.fromFile(CARS_IMPORT_PATH, CarImportWrapperDTO.class);
        List<CarImportDTO> importDTOs = importWrapperDTO.getCarImportDTOList();

// Foreach цикъл, а него булева променлива за валидации:

        for (CarImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String plateNumber = importDTO.getPlateNumber();
            if (this.carRepository.findByPlateNumber(plateNumber).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_CAR_FORMAT, importDTO.getCarMake(), importDTO.getCarModel()));
                Car car = this.modelMapper.map(importDTO, Car.class);
                this.carRepository.saveAndFlush(car);
            } else {
                sb.append(String.format(INVALID_CAR));
            }
        }


        return sb.toString();
    }
}
