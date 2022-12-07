package com.example.nextleveltechnologies.controllers;

import com.example.nextleveltechnologies.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportXMLController {
    private final CompanyService companyService;

    @Autowired
    public ImportXMLController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        boolean companiesImported = this.companyService.areImported();

        boolean[] importStatuses = {companiesImported, false, false};

        model.addAttribute("areImported", importStatuses);
        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String viewImportCompanies(Model model) throws IOException {
        String companiesXML = this.companyService.getXMLContent();
        model.addAttribute("companies", companiesXML);
        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies() throws JAXBException, IOException {
        this.companyService.importCompanies();

        return "redirect:/import/xml";
    }



}
