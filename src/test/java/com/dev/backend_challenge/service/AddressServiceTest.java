package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.Address.AddressCreateDTO;
import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.dto.Address.AddressUpdateDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.repository.AddressRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {
    @InjectMocks
    private AddressService addressService;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ObjectMapper objectMapper;

    private Address address;
    private AddressDTO addressDTO;
    private AddressCreateDTO addressCreateDTO;
    private AddressUpdateDTO addressUpdateDTO;
    private Address updatedAddress;
    private AddressDTO updatedAddressDTO;
    private User user;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        User user = User.builder()
                .id("1")
                .build();
        this.user = user;

        this.address = Address.builder()
                .id(1)
                .street("Street")
                .number("123")
                .district("District")
                .city("City")
                .state("State")
                .cep("12345678")
                .user(user)
                .build();

        this.addressDTO = AddressDTO.builder()
                .id(this.address.getId())
                .street(this.address.getStreet())
                .number(this.address.getNumber())
                .district(this.address.getDistrict())
                .city(this.address.getCity())
                .state(this.address.getState())
                .cep(this.address.getCep())
                .build();

        this.addressUpdateDTO = AddressUpdateDTO.builder()
                .state("New State")
                .street("New Street")
                .build();

        this.updatedAddress = Address.builder()
                .id(this.address.getId())
                .street(this.addressUpdateDTO.getStreet())
                .number(this.address.getNumber())
                .district(this.address.getDistrict())
                .city(this.address.getCity())
                .state(this.addressUpdateDTO.getState())
                .cep(this.address.getCep())
                .user(this.address.getUser())
                .build();

        this.updatedAddressDTO = AddressDTO.builder()
                .id(this.updatedAddress.getId())
                .street(this.updatedAddress.getStreet())
                .number(this.updatedAddress.getNumber())
                .district(this.updatedAddress.getDistrict())
                .city(this.updatedAddress.getCity())
                .state(this.updatedAddress.getState())
                .cep(this.updatedAddress.getCep())
                .build();

    }

    @Test
    void updateAddressOfUser() throws JsonMappingException {
        when(addressRepository.findByUserId(anyString())).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);
        when(objectMapper.convertValue(updatedAddress, AddressDTO.class)).thenReturn(updatedAddressDTO);

        AddressDTO result = addressService.updateAddressOfUser("1", addressUpdateDTO);

        verify(addressRepository, times(1)).findByUserId("1");
        verify(addressRepository, times(1)).save(any(Address.class));
        verify(objectMapper, times(1)).convertValue(updatedAddress, AddressDTO.class);

        assertEquals(updatedAddressDTO, result);
    }

    @Test
    void create() {
        when(objectMapper.convertValue(addressCreateDTO, Address.class)).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(objectMapper.convertValue(address, AddressDTO.class)).thenReturn(addressDTO);

        AddressDTO result = addressService.create(addressCreateDTO, this.user);

        verify(objectMapper, times(1)).convertValue(addressCreateDTO, Address.class);
        verify(addressRepository, times(1)).save(address);
        verify(objectMapper, times(1)).convertValue(address, AddressDTO.class);

        assertEquals(addressDTO, result);
        assertEquals(user, address.getUser());
    }

    @Test
    void findAll() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
        when(objectMapper.convertValue(address, AddressDTO.class)).thenReturn(addressDTO);

        List<AddressDTO> result = addressService.findAll();

        verify(addressRepository, times(1)).findAll();
        verify(objectMapper, times(1)).convertValue(address, AddressDTO.class);

        assertEquals(1, result.size());
        assertEquals(addressDTO, result.getFirst());
    }

    @Test
    void findOneByUserId() {
        when(addressRepository.findByUserId(anyString())).thenReturn(address);
        when(objectMapper.convertValue(address, AddressDTO.class)).thenReturn(addressDTO);

        AddressDTO result = addressService.findOneByUserId("1");

        verify(addressRepository, times(1)).findByUserId("1");
        verify(objectMapper, times(1)).convertValue(address, AddressDTO.class);

        assertEquals(addressDTO, result);
    }
}