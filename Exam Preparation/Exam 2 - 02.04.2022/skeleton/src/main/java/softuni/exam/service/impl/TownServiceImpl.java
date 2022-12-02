package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.validations.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_TOWN;
import static softuni.exam.constants.Messages.VALID_TOWN_FORMAT;
import static softuni.exam.constants.Paths.TOWNS_IMPORT_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtils validationUtils, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_IMPORT_PATH));
    }

    @Override
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        final List<TownImportDTO> towns = Arrays.stream(gson.fromJson(readTownsFileContent(),
                TownImportDTO[].class)).toList();

        for (TownImportDTO townDTO : towns) {
            boolean isValid = this.validationUtils.isValid(townDTO);

            if (this.townRepository.findByTownName(townDTO.getTownName()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_TOWN_FORMAT, townDTO.getTownName(), townDTO.getPopulation()));
                this.townRepository.saveAndFlush(modelMapper.map(townDTO, Town.class));
            } else {
                sb.append(String.format(INVALID_TOWN));
            }
        }

        return sb.toString();
    }
}
