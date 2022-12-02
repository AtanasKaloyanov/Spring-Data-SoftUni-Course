package com.example.football.service.impl;

import com.example.football.models.dto.town.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.validations.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.example.football.constants.Messages.INVALID_TOWN;
import static com.example.football.constants.Messages.VALID_TOWN_FORMAT;
import static com.example.football.constants.Paths.TOWNS_IMPORT_PATH;


@Service
public class TownServiceImpl implements TownService {
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, TownRepository townRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
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

        final List<TownImportDTO> townDTOs = Arrays.stream(gson.fromJson(readTownsFileContent(), TownImportDTO[].class))
                .toList();

        for (TownImportDTO townDTO : townDTOs) {
            boolean isValid = this.validationUtils.isValid(townDTO);

            String currentTownName = townDTO.getName();

            if (this.townRepository.findByName(currentTownName).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_TOWN_FORMAT, townDTO.getName(), townDTO.getPopulation()));
                Town town = this.modelMapper.map(townDTO, Town.class);
                this.townRepository.saveAndFlush(town);
            } else {
                sb.append(String.format(INVALID_TOWN));
            }
        }

        return sb.toString();
    }


}
