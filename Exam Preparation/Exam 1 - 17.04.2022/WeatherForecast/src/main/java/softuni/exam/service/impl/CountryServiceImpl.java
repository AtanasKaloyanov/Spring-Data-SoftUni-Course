package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.validations.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.Messages.INVALID_COUNTRY;
import static softuni.exam.constants.Messages.VALID_COUNTRY_FORMAT;
import static softuni.exam.constants.Paths.COUNTRIES_IMPORT_PATH;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(COUNTRIES_IMPORT_PATH));
    }

    @Override
    public String importCountries() throws IOException {
      CountryImportDTO[] countryImportDTOS = gson.fromJson(readCountriesFromFile(), CountryImportDTO[].class);

        final StringBuilder sb = new StringBuilder();

        CountryImportDTO[] coontriesArray = gson.fromJson(readCountriesFromFile(), CountryImportDTO[].class);

        Arrays.stream(coontriesArray)
                .forEach(countryDto -> {
                    boolean isValid = this.validationUtils.isValid(countryDto);

                    String currentDTOCountryName = countryDto.getCountryName();
                    if (this.countryRepository.findByCountryName(currentDTOCountryName).isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        sb.append(String.format(VALID_COUNTRY_FORMAT, countryDto.getCountryName(), countryDto.getCurrency()));
                        this.countryRepository.saveAndFlush(this.modelMapper.map(countryDto, Country.class));
                    } else {
                        sb.append(String.format(INVALID_COUNTRY));
                    }

                });

        return sb.toString();
    }
}
