package com.example.football.service.impl;

import com.example.football.models.dto.stat.StatImportDTO;
import com.example.football.models.dto.stat.StatImportWrapperDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.validations.ValidationUtils;
import com.example.football.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.example.football.constants.Messages.INVALID_STAT;
import static com.example.football.constants.Messages.VALID_STAT_FORMAT;
import static com.example.football.constants.Paths.PLAYERS_IMPORT_PATH;
import static com.example.football.constants.Paths.STATS_IMPORT_PATH;

@Service
public class StatServiceImpl implements StatService {
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final StatRepository statRepository;

    @Autowired
    public StatServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, StatRepository statRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.statRepository = statRepository;
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_IMPORT_PATH));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        StatImportWrapperDTO statImportWrapperDTO = this.xmlParser.fromFile(STATS_IMPORT_PATH, StatImportWrapperDTO.class);
        List<StatImportDTO> importDTOs = statImportWrapperDTO.getStatImportDTOList();

// Foreach цикъл, а него булева променлива за валидации:

        for (StatImportDTO statmportDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(statmportDTO);

            Float passing = statmportDTO.getPassing();
            Float shooting = statmportDTO.getShooting();
            Float endurance = statmportDTO.getEndurance();

            if (statRepository.findStatByShootingPassingAndEndurance(shooting, passing, endurance).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_STAT_FORMAT, shooting, passing, endurance));
                Stat stat = modelMapper.map(statmportDTO, Stat.class);
                this.statRepository.saveAndFlush(stat);
            } else {
                sb.append(String.format(INVALID_STAT));
            }

        }

        return sb.toString();
    }
}
