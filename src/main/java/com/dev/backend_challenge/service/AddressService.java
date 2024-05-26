package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.Address.AddressCreateDTO;
import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    public AddressDTO create(AddressCreateDTO addressCreateDTO, User user) {
        Address address = this.objectMapper.convertValue(addressCreateDTO, Address.class);

        address.setUser(user);

        Address createdAddress = this.addressRepository.save(address);

        return this.objectMapper.convertValue(createdAddress, AddressDTO.class);
    }

    public AddressDTO findOneByUserId(String userId){
        Address address = this.addressRepository.findByUserId(userId);

        return this.objectMapper.convertValue(address, AddressDTO.class);
    }
}
