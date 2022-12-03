package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.part.PartImporDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_PART;
import static softuni.exam.constants.Messages.VALID_PART_FORMAT;
import static softuni.exam.constants.Paths.PARTS_IMPORT_PATH;

@Service
public class PartServiceImpl implements PartService {
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, PartRepository partRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
    }

    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_IMPORT_PATH));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();

        final List<PartImporDTO> importDTOs = Arrays.stream(gson.fromJson(readPartsFileContent(), PartImporDTO[].class))
                .toList();

        for (PartImporDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String name = importDTO.getPartName();
            if (this.partRepository.findByPartName(name).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_PART_FORMAT, importDTO.getPartName(), importDTO.getPrice()));
                Part part = this.modelMapper.map(importDTO, Part.class);
                this.partRepository.saveAndFlush(part);
            } else {
                sb.append(String.format(INVALID_PART));
            }

        }

        return sb.toString();
    }
}
