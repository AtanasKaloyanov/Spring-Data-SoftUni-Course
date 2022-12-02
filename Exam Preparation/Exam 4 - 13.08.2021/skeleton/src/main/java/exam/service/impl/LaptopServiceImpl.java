package exam.service.impl;

import com.google.gson.Gson;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.util.validation.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static exam.constant.Paths.CUSTOMERS_IMPORT_PATH;
import static exam.constant.Paths.LAPTOPS_IMPORT_PATH;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    private final LaptopRepository laptopRepository;

    public LaptopServiceImpl(Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, LaptopRepository laptopRepository) {
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.laptopRepository = laptopRepository;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOPS_IMPORT_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        return null;
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
