package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Offer.OfferImporDTO;
import softuni.exam.models.dto.Offer.OfferImporWrapperDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.validations.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.OFFERS_IMPORT_PATH;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;

    private final ApartmentRepository apartmentRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_IMPORT_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferImporWrapperDTO offerImportWrapperDTO = this.xmlParser.fromFile(OFFERS_IMPORT_PATH, OfferImporWrapperDTO.class);
        List<OfferImporDTO> offerImporDTOs = offerImportWrapperDTO.getOffertImportDTOs();

        for (OfferImporDTO offerImporDTO : offerImporDTOs) {
            // If agent with the given name doesn’t already exist in the DB return "Invalid offer".
            // The provided apartment ids will always be valid.
            // Format the price to the second digit after the decimal point.

            boolean isValid = this.validationUtils.isValid(offerImporDTO);

            String agentName = offerImporDTO.getAgentNameWrapperDTO().getName();
            if (this.agentRepository.findByFirstName(agentName).isEmpty()) {
                isValid = false;
            }

            if (isValid) {
                sb.append(String.format(VALID_OFFER_FORMAT, offerImporDTO.getPrice()));
                Offer offer = this.modelMapper.map(offerImporDTO, Offer.class);
                Agent agent = this.agentRepository.findByFirstName(agentName).get();

                Long apartmentId = offerImporDTO.getApartmentIdWrapperDTO().getId();
                Apartment apartament = this.apartmentRepository.findApartmentById(apartmentId).get();

                offer.setAgent(agent);
                offer.setApartment(apartament);

                this.offerRepository.saveAndFlush(offer);
            } else {
                sb.append(String.format(INVALID_OFFER));
            }

        }

        return sb.toString();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();

        ApartmentType apartmentType = ApartmentType.three_rooms;

        List<Offer> searchedOffers = this.offerRepository.findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(apartmentType); //findSearchedOffers(apartmentType);

        for (Offer searchedOffer : searchedOffers) {
            sb.append(String.format(AGENT_NAME_WITH_OFFER_PRINT_RESULT, searchedOffer.getAgent().getFirstName(), searchedOffer.getAgent().getLastName(), searchedOffer.getId()));
            sb.append(String.format(APARTMENT_AREA_PRINT_RESULT, searchedOffer.getApartment().getArea()));
            sb.append(String.format(TOWN_NAME_PRINT_RESULT, searchedOffer.getApartment().getTown().getTownName()));
            sb.append(String.format(PRICE_PRINT_RESULT, searchedOffer.getPrice()));
        }

        return sb.toString();
    }
}

// public static final String AGENT_NAME_WITH_OFFER_PRINT_RESULT = "Agent %s %s with offer №%d:%n";
//    public static final String APERTMENT_AREA_PRINT_RESULT = "-Apartment area: %.2f";
//    public static final String TOWN_NAME_PRINT_RESULT = "--Town: %.s";
//    public static final String PRICE_PRINT_RESULT = "---Price: %.2f$%n";