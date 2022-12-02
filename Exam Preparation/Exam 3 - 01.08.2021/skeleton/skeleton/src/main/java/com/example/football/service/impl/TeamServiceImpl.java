package com.example.football.service.impl;

import com.example.football.models.dto.team.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.TEAMS_IMPORT_PATH;

@Service
public class TeamServiceImpl implements TeamService {

    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    private final TownRepository townRepository;

    @Autowired
    public TeamServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, TeamRepository teamRepository, TownRepository townRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_IMPORT_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();

        final List<TeamImportDTO> teamDTOs = Arrays.stream(gson.fromJson(readTeamsFileContent(), TeamImportDTO[].class))
                .toList();

        for (TeamImportDTO teamDTO : teamDTOs) {
            boolean isValid = this.validationUtils.isValid(teamDTO);

            String teamName = teamDTO.getName();

            if (this.teamRepository.findByName(teamName).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_TEAM_FORMAT, teamDTO.getName(), teamDTO.getFanBase()));
                Team team = this.modelMapper.map(teamDTO, Team.class);
                String currentTownName = teamDTO.getTownName();
                Town town = this.townRepository.findByName(currentTownName).get();
                team.setTownName(town);
                this.teamRepository.saveAndFlush(team);
            } else {
                sb.append(String.format(INVALID_TEAM));
            }
        }

        return sb.toString();
    }
}
