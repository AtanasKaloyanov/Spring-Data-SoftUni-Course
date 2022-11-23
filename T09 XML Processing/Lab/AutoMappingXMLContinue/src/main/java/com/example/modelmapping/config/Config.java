package com.example.modelmapping.config;

import com.example.modelmapping.models.dto.addresses.AddressDTO;
import com.example.modelmapping.models.dto.addresses.AddressXMLDTO;
import com.example.modelmapping.models.dto.addresses.CountryXMLDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Gson gson() throws JAXBException {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                //  .setPrettyPrinting()
                .create();
    }

    @Bean("addressContext")
    @Primary
    public JAXBContext createAddressContext() throws JAXBException {
        return JAXBContext.newInstance(AddressXMLDTO.class);
    }

    @Bean("countryContext")
    public JAXBContext createCountryContext() throws JAXBException {
        return JAXBContext.newInstance(CountryXMLDTO.class);
    }

}
