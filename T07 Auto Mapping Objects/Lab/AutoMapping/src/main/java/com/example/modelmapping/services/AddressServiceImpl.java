package com.example.modelmapping.services;

import com.example.modelmapping.models.dto.AddressDTO;
import com.example.modelmapping.models.entities.Address;
import com.example.modelmapping.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    private final ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public Address create(AddressDTO data) {

        Address address = mapper.map(data, Address.class);

        return this.addressRepository.save(address);
    }
}
