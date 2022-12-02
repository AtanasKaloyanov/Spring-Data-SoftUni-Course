package com.example.football.service.impl;

import com.example.football.models.dto.player.PlayerImportDTO;
import com.example.football.models.dto.player.PlayerWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.validations.ValidationUtils;
import com.example.football.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.PLAYERS_IMPORT_PATH;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, PlayerRepository playerRepository, TownRepository townRepository, StatRepository statRepository, TeamRepository teamRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_IMPORT_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        PlayerWrapperDTO playerWrapperDTO = this.xmlParser.fromFile(PLAYERS_IMPORT_PATH, PlayerWrapperDTO.class);
        List<PlayerImportDTO> importDTOs = playerWrapperDTO.getPlayerImportDTOList();

        for (PlayerImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String currentEmail = importDTO.getEmail();
            if (this.playerRepository.findByEmail(currentEmail).isPresent()) {
                isValid = false;
            }

            Long id = importDTO.getStat().getId();
            if (this.statRepository.findById(id).isEmpty()) {
                isValid = false;
            }

            if (isValid) {
                Player player = this.modelMapper.map(importDTO, Player.class);
                String townName = importDTO.getTown().getName();
                String teamName = importDTO.getTeam().getName();
                Long statId = importDTO.getStat().getId();

                Town town = this.townRepository.findByName(townName).get();
                Team team = this.teamRepository.findByName(teamName).get();
                Stat stat = this.statRepository.findById(statId).get();

                player.setTown(town);
                player.setTeam(team);
                player.setStat(stat);

                sb.append(String.format(VALID_PLAYER_FORMAT, importDTO.getFirstName(), importDTO.getLastName(), importDTO.getPosition()));
                this.playerRepository.saveAndFlush(player);
            } else {
                sb.append(String.format(INVALID_PLAYER));
            }
        }

        // •	If the player's email already exists in the DB return "Invalid Player".
        //•	The provided town and team names will always be valid.
        //•	The Stat id referenced to the valid Stat id.

        return sb.toString();

    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();

        LocalDate firstDate = LocalDate.parse("1995-01-01");
        LocalDate secondDate = LocalDate.parse("2003-01-01");
        List<Player> players = this.playerRepository.FindTheBestPlayers(firstDate, secondDate).get();

        for (Player player : players) {
            sb.append(String.format(PLAYER_PRINT_RESULT, player.getFirstName(), player.getLastName()));
            sb.append(String.format(POSITION_PRINT_RESULT, player.getPosition()));
            sb.append(String.format(TEAM_PRINT_RESULT, player.getTeam().getName()));
            sb.append(String.format(STADIUM_PRINT_RESULT, player.getTeam().getStadiumName()));
        }

        return sb.toString();

        // public static final String PLAYER_PRINT_RESULT = "Player - %s %s%n";
        //    public static final String POSITION_PRINT_RESULT = "Position - %s%n";
        //    public static final String TEAM_PRINT_RESULT = "Team - %s%n";
        //    public static final String STADIUM_PRINT_RESULT = "Stadium - %s%n";
    }
}
