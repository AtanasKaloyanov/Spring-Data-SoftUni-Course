package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.mechanic.MechanicImportDTO;
import softuni.exam.models.dto.part.PartImporDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.MECHANICS_IMPORT_PATH;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final MechanicRepository mechanicRepository;

    @Autowired
    public MechanicServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, MechanicRepository mechanicRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.mechanicRepository = mechanicRepository;
    }


    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_IMPORT_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();
        final List<MechanicImportDTO> importDTOs = Arrays.stream(gson.fromJson(readMechanicsFromFile(), MechanicImportDTO[].class))
                .toList();

        for (MechanicImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String email = importDTO.getEmail();
            if (this.mechanicRepository.findByEmail(email).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_MECHANIC_FORMAT, importDTO.getFirstName(), importDTO.getLastName()));
                Mechanic mechanic = this.modelMapper.map(importDTO, Mechanic.class);
                this.mechanicRepository.saveAndFlush(mechanic);
            } else {
                sb.append(String.format(INVALID_MECHANIC));
            }
        }


        return sb.toString();

    }
}
