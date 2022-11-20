package com.example.modelmapping;

import com.example.modelmapping.models.dto.addresses.CreateAddressDTO;
import com.example.modelmapping.models.dto.CompanyDTO;
import com.example.modelmapping.models.dto.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class JsonTestMain implements CommandLineRunner {

    private final Scanner scanner;

    private final Gson gson;

    public JsonTestMain(Scanner scanner) {

        this.scanner = scanner;

        this.gson = new GsonBuilder()
               .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            //     .setPrettyPrinting()
                .create();
    }

    class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer{

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }

        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }

    @Override
    public void run(String... args) throws Exception {


        CreateAddressDTO address1 = new CreateAddressDTO("Burgas", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO1 = new CreateEmployeeDTO("First", "Last", BigDecimal.ONE, LocalDate.now(), address1);

        CreateAddressDTO address2 = new CreateAddressDTO("Sofia", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO2 = new CreateEmployeeDTO("Second", "Last", BigDecimal.ONE, LocalDate.now(), address2);

        CreateAddressDTO address3 = new CreateAddressDTO("Razgrad", "Bulgaria");
        CreateEmployeeDTO createEmployeeDTO3 = new CreateEmployeeDTO("Third", "Last", BigDecimal.ONE, LocalDate.now(), address3);

        CompanyDTO mega = new CompanyDTO("Mega", List.of(createEmployeeDTO1, createEmployeeDTO2, createEmployeeDTO3));

        String output = this.gson.toJson(mega);

        System.out.println(output);

        String input = scanner.nextLine();
        CompanyDTO companyDTO = this.gson.fromJson(input, CompanyDTO.class);

        System.out.println();

    }


}
