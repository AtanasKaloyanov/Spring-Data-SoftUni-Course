package com.example.modelmapping.services;


import com.example.modelmapping.models.dto.AddressDTO;
import com.example.modelmapping.models.entities.Address;

public interface AddressService {
   Address create(AddressDTO data);
}
