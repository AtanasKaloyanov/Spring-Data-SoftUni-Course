package exam.service.impl;

import exam.model.dto.town.TownImportDTO;
import exam.model.dto.town.TownImportWrapperDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.validation.ValidationUtils;
import exam.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static exam.constant.Messages.INVALID_TOWN;
import static exam.constant.Messages.VALID_TOWN_FORMAT;
import static exam.constant.Paths.CUSTOMERS_IMPORT_PATH;
import static exam.constant.Paths.TOWNS_IMPORT_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final TownRepository townRepository;

    public TownServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, TownRepository townRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
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
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        TownImportWrapperDTO importWrapperDTO = this.xmlParser.fromFile(TOWNS_IMPORT_PATH, TownImportWrapperDTO.class);
        List<TownImportDTO> importDTOs = importWrapperDTO.getTownImportDTOList();

        for (TownImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String name = importDTO.getName();
            if (this.townRepository.findByName(name).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_TOWN_FORMAT, importDTO.getName()));

                Town town = this.modelMapper.map(importDTO, Town.class);
                this.townRepository.saveAndFlush(town);
            } else {
                sb.append(String.format(INVALID_TOWN));
            }
        }


        return sb.toString();
    }
}
