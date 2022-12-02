package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDTO;
import softuni.exam.models.dto.ForecastWrapperImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;

import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.validations.ValidationUtils;
import softuni.exam.util.xmlParsing.XmlParser;
import softuni.exam.models.entity.enums.DayOfWeek;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.util.List;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.FORECASTS_IMPORT_PATH;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_IMPORT_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        ForecastWrapperImportDTO forecastWrapperImportDTO =  this.xmlParser.fromFile(FORECASTS_IMPORT_PATH, ForecastWrapperImportDTO.class);
        List<ForecastImportDTO> forecastImportDTOs = forecastWrapperImportDTO.getForecasts();

        for (ForecastImportDTO forecastImportDTO : forecastImportDTOs) {
            boolean isValid = this.validationUtils.isValid(forecastImportDTO);

            Long curretCityId = forecastImportDTO.getCity();
            if (cityRepository.findById(curretCityId).isEmpty()) {
                isValid = false;
            }

            City cityByID = this.cityRepository.findById(curretCityId).get();
            DayOfWeek currentDayOfWeek = forecastImportDTO.getDayOfWeek();

            if (forecastRepository.findForeCastWithTownAndDayOfWeek(cityByID, currentDayOfWeek) != null) {
                isValid = false;
            }
                // Ако в базата за прогнозите има (методът връща нещо различно от нъл) прогноза, която има град, който отговаря на ид на града от ДТО-то
                // и едновремено с това прогнозата има същия ден от седмицата, както ДТО-то, то прогнозата е невалидна.

            if (isValid) {
                sb.append(String.format(VALID_FORECAST_FORMAT, forecastImportDTO.getDayOfWeek(), forecastImportDTO.getMaxTemperature()));
                Forecast forecast = modelMapper.map(forecastImportDTO, Forecast.class);
                City city = this.cityRepository.findById(forecastImportDTO.getCity()).get();
                forecast.setCity(city);
                this.forecastRepository.saveAndFlush(forecast);
            } else {
                sb.append(String.format(INVALID_FORECAST));
            }

        }

        return sb.toString();
    }


    @Override
    public String exportForecasts() {
        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;
        Long population = 150000L;

         List<Forecast> forecasts = this.forecastRepository.findAllByDayOfWeekAndCity(dayOfWeek, population);

         StringBuilder sb = new StringBuilder();
        for (Forecast forecast : forecasts) {

            sb.append(String.format(CTY_NAME_PRINT_RESULT, forecast.getCity().getCityName()));
            sb.append(String.format(MIN_TEMPERATURE_PRINT_RESULT, forecast.getMinTemperature()));
            sb.append(String.format(MAX_TEMPERATURE_PRINT_RESULT, forecast.getMaxTemperature()));
            sb.append(String.format(SUNRISE_RESULT, forecast.getSunrise()));
            sb.append(String.format(SUNSET_PRINT_RESULT, forecast.getSunset()));
        }

         return sb.toString();
    }


}
