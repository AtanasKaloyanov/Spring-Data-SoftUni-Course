package exam.service.impl;

import exam.model.dto.shop.ShopImportDTO;
import exam.model.dto.shop.ShopImportWrapperDTO;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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

import static exam.constant.Messages.INVALID_SHOP;
import static exam.constant.Messages.VALID_SHOP_FORMAT;
import static exam.constant.Paths.*;

@Service
public class ShopServiceImpl implements ShopService {
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    private final ShopRepository shopRepository;

    private final TownRepository townRepository;

    public ShopServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils, ShopRepository shopRepository, TownRepository townRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOPS_IMPORT_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        ShopImportWrapperDTO importWrapperDTO = this.xmlParser.fromFile(SHOPS_IMPORT_PATH, ShopImportWrapperDTO.class);
        List<ShopImportDTO> importDTOs = importWrapperDTO.getShopImportDTOS();

        for (ShopImportDTO importDTO : importDTOs) {
            boolean isValid = this.validationUtils.isValid(importDTO);

            String name = importDTO.getName();
            if (this.shopRepository.findByName(name).isPresent()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_SHOP_FORMAT, importDTO.getName(), importDTO.getIncome()));
                String townName = importDTO.getTownWrapperDTO().getName();
                Town town = this.townRepository.findByName(townName).get();
                Shop shop = this.modelMapper.map(importDTO, Shop.class);
                shop.setTown(town);
                this.shopRepository.saveAndFlush(shop);
            } else {
                sb.append(String.format(INVALID_SHOP));
            }
        }

        return sb.toString();
    }
}
