package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.agent.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.validations.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_AGENT;
import static softuni.exam.constants.Messages.VALID_AGENT_FORMAT;
import static softuni.exam.constants.Paths.AGENTS_IMPORT_PATH;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final Gson gson;

    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, ModelMapper modelMapper, ValidationUtils validationUtils, Gson gson) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_IMPORT_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();

        final List<AgentImportDTO> agentDTOs = Arrays.stream(gson.fromJson(readAgentsFromFile(),
                AgentImportDTO[].class)).toList();

        for (AgentImportDTO agentDTO : agentDTOs) {
            boolean isValid = this.validationUtils.isValid(agentDTO);

            if (this.agentRepository.findByFirstName(agentDTO.getFirstName()).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                Town town = this.townRepository.findByTownName(agentDTO.getTown()).get();
                Agent agent = modelMapper.map(agentDTO, Agent.class);
                agent.setTown(town);
                sb.append(String.format(VALID_AGENT_FORMAT, agentDTO.getFirstName(), agentDTO.getLastName()));
                this.agentRepository.saveAndFlush(agent);
            } else {
                sb.append(String.format(INVALID_AGENT));
            }
        }

        return sb.toString();
    }
}
