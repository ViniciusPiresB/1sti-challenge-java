package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.address.AddressCreateDTO;
import com.dev.backend_challenge.dto.address.AddressDTO;
import com.dev.backend_challenge.dto.address.AddressUpdateDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.repository.AddressRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    public AddressDTO updateAddressOfUser(String userId, AddressUpdateDTO addressUpdateDTO) throws JsonMappingException {
        Address address = this.addressRepository.findByUserId(userId);

        this.objectMapper.updateValue(address, addressUpdateDTO);

        Address updatedAddress = this.addressRepository.save(address);

        return this.objectMapper.convertValue(updatedAddress, AddressDTO.class);
    }

    public AddressDTO create(AddressCreateDTO addressCreateDTO, User user) {
        Address address = this.objectMapper.convertValue(addressCreateDTO, Address.class);

        address.setUser(user);

        Address createdAddress = this.addressRepository.save(address);

        return this.objectMapper.convertValue(createdAddress, AddressDTO.class);
    }

    public List<AddressDTO> findAll(){
        return this.addressRepository.findAll().stream().map(address -> objectMapper.convertValue(address, AddressDTO.class)).collect(Collectors.toList());
    }

    public AddressDTO findOneByUserId(String userId){
        Address address = this.addressRepository.findByUserId(userId);

        return this.objectMapper.convertValue(address, AddressDTO.class);
    }
}
