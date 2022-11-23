package com.example.modelmapping;

import com.example.modelmapping.models.dto.addresses.AddressXMLDTO;
import com.example.modelmapping.models.dto.addresses.CountryXMLDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

@Component
public class XMLTestMain implements CommandLineRunner {

    private final JAXBContext addressJaxbContext;

    private final JAXBContext countryContext;


    public XMLTestMain(@Qualifier("addressContext") JAXBContext addressJaxbContext, JAXBContext countryContext) {
        this.addressJaxbContext = addressJaxbContext;
        this.countryContext = countryContext;
    }

    @Override
    public void run(String... args) throws Exception {
        CountryXMLDTO countryXMLDTO = new CountryXMLDTO("Bulgaria");
        List<CountryXMLDTO> countryXMLDTOList = new ArrayList<>(List.of(countryXMLDTO, countryXMLDTO, countryXMLDTO));
        AddressXMLDTO addressXMLDTO1 = new AddressXMLDTO(5, "Bulgaria", "Vidin");

    //    JAXBContext context = JAXBContext.newInstance(AddressXMLDTO.class);
        Marshaller countryMarschall = countryContext.createMarshaller();
        countryMarschall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        countryMarschall.marshal(countryXMLDTO, System.out);

        Marshaller addressMarshall = addressJaxbContext.createMarshaller();
        addressMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        addressMarshall.marshal(addressXMLDTO1, System.out);


//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        AddressXMLDTO addressXMLDTO = (AddressXMLDTO) unmarshaller.unmarshal(System.in);
//
//        System.out.println(addressXMLDTO);
    }
}
