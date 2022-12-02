package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Apartment.ApartmentImportDTO;
import softuni.exam.models.dto.Apartment.ApartmentImportWrapperDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.validations.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_APARTMENT;
import static softuni.exam.constants.Messages.VALID_APARTMENT_FORMAT;
import static softuni.exam.constants.Paths.APARTMENTS_IMPORT_PATH;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_IMPORT_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        ApartmentImportWrapperDTO importWrapperDTO = this.xmlParser.fromFile(APARTMENTS_IMPORT_PATH, ApartmentImportWrapperDTO.class);
        List<ApartmentImportDTO> importDTOs = importWrapperDTO.getApartmentDTOs();

        for (ApartmentImportDTO importDTO : importDTOs) {
            boolean isValid = validationUtils.isValid(importDTO);

            String townName = importDTO.getTown();
            Double area = importDTO.getArea();

            if (this.apartmentRepository.findApartmentByTownNameAndArea(townName, area) != null) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_APARTMENT_FORMAT, importDTO.getApartmentType(), importDTO.getArea()));
                Apartment apartment = modelMapper.map(importDTO, Apartment.class);
                Town town = this.townRepository.findByTownName(townName).get();
                apartment.setTown(town);
                this.apartmentRepository.saveAndFlush(apartment);
            } else {
                sb.append(String.format(INVALID_APARTMENT));
            }
        }

        return sb.toString();
        // If the apartment with the same town name and area already exists in the DB return "Invalid apartment".
    }
}
