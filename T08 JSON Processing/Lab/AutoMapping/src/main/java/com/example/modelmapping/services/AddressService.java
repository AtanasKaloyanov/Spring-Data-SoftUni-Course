package com.example.modelmapping.services;


import com.example.modelmapping.models.dto.addresses.AddressDTO;
import com.example.modelmapping.models.dto.addresses.CreateAddressDTO;

public interface AddressService {
   AddressDTO create(CreateAddressDTO data);
}
