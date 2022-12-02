package softuni.exam.service;

import softuni.exam.models.entity.City;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface ForecastService {

    boolean areImported();

    String readForecastsFromFile() throws IOException;
	
	String importForecasts() throws IOException, JAXBException;

    String exportForecasts();

}
