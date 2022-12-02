package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.validations.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Messages.INVALID_CITY;
import static softuni.exam.constants.Messages.VALID_CITY_FORMAT;
import static softuni.exam.constants.Paths.CITIES_IMPORT_PATH;


@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;


    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(CITIES_IMPORT_PATH));
    }

    @Override
    public String importCities() throws IOException {

        StringBuilder sb = new StringBuilder();

        final List<CityImportDTO> cities = Arrays.stream(gson.fromJson(readCitiesFileContent(), CityImportDTO[].class))
                .toList();

        for (CityImportDTO cityDTO : cities) {
            boolean isValid = this.validationUtils.isValid(cityDTO);

            if (this.cityRepository.findByCityName(cityDTO.getCityName()).isPresent()) {
                isValid = false;
            }

            Long currentCountryID = cityDTO.getCountry();
            if (this.countryRepository.findById(currentCountryID).isEmpty()) {
                isValid = false;
            }

            if (isValid) {
                City city = modelMapper.map(cityDTO, City.class);
                Country country = this.countryRepository.findById(currentCountryID).get();
                city.setCountry(country);

                this.cityRepository.saveAndFlush(city);
                sb.append(String.format(VALID_CITY_FORMAT, cityDTO.getCityName(), cityDTO.getPopulation()));
            } else {
                sb.append(String.format(INVALID_CITY));
            }

        }

        return sb.toString();
    }


}
