package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.customer.CustomerImportDTO;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.validation.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static exam.constant.Messages.*;
import static exam.constant.Paths.CUSTOMERS_IMPORT_PATH;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    private final TownRepository townRepository;

    public CustomerServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, CustomerRepository customerRepository, TownRepository townRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_IMPORT_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();

        final List<CustomerImportDTO> customers = Arrays.stream(gson.fromJson(readCustomersFileContent(), CustomerImportDTO[].class))
                .toList();

        for (CustomerImportDTO importDTO : customers) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String email = importDTO.getEmail();
            if (this.customerRepository.findByEmail(email).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_CUSTOMER_FORMAT, importDTO.getFirstName(), importDTO.getLastName(), importDTO.getEmail()));
                Customer currentCustomer = this.modelMapper.map(importDTO, Customer.class);
                String name = importDTO.getTown().getName();
                Town town = this.townRepository.findByName(name).get();

                currentCustomer.setTown(town);
                this.customerRepository.saveAndFlush(currentCustomer);
            } else {
                sb.append(String.format(String.format(INVALID_CUSTOMER)));
            }
        }

        return sb.toString();
    }


}
