package com.example.nextleveltechnologies.service;


import com.example.nextleveltechnologies.dtos.ImportCompaniesDTO;
import com.example.nextleveltechnologies.dtos.ImportCompanyDTO;
import com.example.nextleveltechnologies.models.companies.Company;
import com.example.nextleveltechnologies.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.mapper = modelMapper;
    }

    public String getXMLContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xmls", "companies.xml");
        List<String> lines = Files.readAllLines(path);
        return String.join("\n", lines);
    }

    public void importCompanies() throws IOException, JAXBException {
        String xmlContent = this.getXMLContent();
        ByteArrayInputStream stream = new ByteArrayInputStream(xmlContent.getBytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(ImportCompaniesDTO.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportCompaniesDTO companies = (ImportCompaniesDTO) unmarshaller.unmarshal(stream);

        List<ImportCompanyDTO> importCompanyDTOS = companies.getCompanies();

        List<Company> entities = importCompanyDTOS
                .stream()
                .map(dto -> this.mapper.map(dto, Company.class))
                .collect(Collectors.toList());

        this.companyRepository.saveAll(entities);
    }

    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }
}
