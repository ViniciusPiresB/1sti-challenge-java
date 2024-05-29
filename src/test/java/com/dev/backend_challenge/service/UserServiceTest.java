package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.address.AddressCreateDTO;
import com.dev.backend_challenge.dto.address.AddressDTO;
import com.dev.backend_challenge.dto.address.AddressUpdateDTO;
import com.dev.backend_challenge.dto.user.UserCreateDTO;
import com.dev.backend_challenge.dto.user.UserDTO;
import com.dev.backend_challenge.dto.user.UserWithAddressDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
import com.dev.backend_challenge.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;
    @Mock
    private AddressService addressService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private PasswordEncoder encoder;

    private final Address fakeAddress = Address.builder()
            .id(1)
            .street("Rua 3")
            .number("2")
            .district("Boa Vista")
            .city("Sao Paulo")
            .cep("010648957")
            .state("SP")
            .user(null)
            .build();

    private final AddressDTO fakeAddressDTO = AddressDTO.builder()
            .id(fakeAddress.getId())
            .street(fakeAddress.getStreet())
            .number(fakeAddress.getNumber())
            .district(fakeAddress.getDistrict())
            .city(fakeAddress.getCity())
            .cep(fakeAddress.getCep())
            .state(fakeAddress.getState())
            .build();

    private final AddressUpdateDTO fakeAddressUpdateDTO = AddressUpdateDTO.builder()
            .street("Updated street")
            .district("Updated district")
            .city("Updated city")
            .build();

    private final AddressDTO fakeAddressUpdatedDTO = AddressDTO.builder()
            .id(fakeAddress.getId())
            .street(fakeAddressUpdateDTO.getStreet())
            .number(fakeAddress.getNumber())
            .district(fakeAddressUpdateDTO.getDistrict())
            .city(fakeAddressUpdateDTO.getCity())
            .cep(fakeAddress.getCep())
            .state(fakeAddress.getState())
            .build();

    private final User fakeUser = User.builder()
            .id("a3718843-5456-4482-9c97-a20f78cbd44e")
            .name("Test User 1")
            .password("encodedPassword")
            .birth(LocalDate.now())
            .status(Status.ACTIVE)
            .typeUser(TypeUser.USER)
            .createdAt(LocalDate.now())
            .createdBy("Admin")
            .updatedAt(null)
            .updatedBy(null)
            .deletedAt(null)
            .deletedBy(null)
            .address(fakeAddress)
            .build();

    private final UserDTO fakeUserDTO = UserDTO.builder()
            .id("a3718843-5456-4482-9c97-a20f78cbd44e")
            .name("Test User 1")
            .birth(LocalDate.now())
            .status(Status.ACTIVE)
            .typeUser(TypeUser.USER)
            .build();

    private final UserWithAddressDTO fakeUserWithAddressDTO = UserWithAddressDTO.builder()
            .id(fakeUserDTO.getId())
            .name(fakeUserDTO.getName())
            .birth(fakeUserDTO.getBirth())
            .status(fakeUserDTO.getStatus())
            .typeUser(fakeUserDTO.getTypeUser())
            .address(fakeAddressDTO)
            .build();

    private final AddressCreateDTO fakeAddressCreateDTO = AddressCreateDTO.builder()
            .street("Rua 3")
            .number("2")
            .district("Boa Vista")
            .city("São Paulo")
            .state("SP")
            .cep("010648957")
            .build();

    private final UserCreateDTO fakeUserCreateDTO = UserCreateDTO.builder()
            .name("Test User 1")
            .cpf("70031242546")
            .password("encodedPassword")
            .birth(LocalDate.now())
            .typeUser(TypeUser.USER)
            .address(fakeAddressCreateDTO)
            .build();

    @BeforeEach
    public void beforeEach(){
        this.fakeAddress.setUser(this.fakeUser);
    }

    @Test
    void createUserSuccessfully() {
        when(objectMapper.convertValue(fakeUserCreateDTO, User.class)).thenReturn(fakeUser);
        when(encoder.encode(fakeUserCreateDTO.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(fakeUser)).thenReturn(fakeUser);
        when(objectMapper.convertValue(fakeUser, UserDTO.class)).thenReturn(fakeUserDTO);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(fakeUserDTO.getCpf());

        UserDTO result = userService.create(fakeUserCreateDTO);

        verify(objectMapper).convertValue(fakeUserCreateDTO, User.class);
        verify(encoder).encode(fakeUserCreateDTO.getPassword());
        verify(userRepository, times(1)).save(fakeUser);
        verify(objectMapper).convertValue(fakeUser, UserDTO.class);

        assertEquals(fakeUserDTO, result);
    }

    @Test
    void findAll() {
        List<User> users = Arrays.asList(fakeUser);

        when(userRepository.findAll()).thenReturn(users);
        when(objectMapper.convertValue(fakeUser, UserDTO.class)).thenReturn(fakeUserDTO);

        List<UserDTO> result = userService.findAll();

        verify(userRepository, times(1)).findAll();
        verify(objectMapper).convertValue(fakeUser, UserDTO.class);

        assertEquals(1, result.size());
        assertEquals(fakeUserDTO, result.get(0));
    }

    @Test
    void findOne() {
        String cpf = fakeUser.getCpf();

        when(userRepository.findByCpf(cpf)).thenReturn(fakeUser);
        when(objectMapper.convertValue(fakeUser, UserDTO.class)).thenReturn(fakeUserDTO);

        UserDTO result = userService.findOne(cpf);

        verify(userRepository, times(1)).findByCpf(cpf);
        verify(objectMapper).convertValue(fakeUser, UserDTO.class);

        assertEquals(fakeUserDTO, result);
    }

    @Test
    void findUserWithAddress() {
        String cpf = fakeUser.getCpf();

        when(userRepository.findByCpf(cpf)).thenReturn(fakeUser);
        when(addressService.findOneByUserId(anyString())).thenReturn(fakeAddressDTO);
        when(objectMapper.convertValue(fakeUser, UserWithAddressDTO.class)).thenReturn(fakeUserWithAddressDTO);

        UserWithAddressDTO result = userService.findUserWithAddress(cpf);

        verify(userRepository, times(1)).findByCpf(cpf);
        verify(addressService, times(1)).findOneByUserId(fakeUser.getId());
        verify(objectMapper).convertValue(fakeUser, UserWithAddressDTO.class);

        assertEquals(fakeUserWithAddressDTO, result);
    }

    @Test
    void updateAddress() throws JsonMappingException {
        String cpf = fakeUser.getCpf();

        when(userRepository.findByCpf(cpf)).thenReturn(fakeUser);
        when(addressService.updateAddressOfUser(cpf, fakeAddressUpdateDTO)).thenReturn(fakeAddressUpdatedDTO);

        AddressDTO result = userService.updateAddress(cpf, fakeAddressUpdateDTO);

        verify(userRepository, times(1)).findByCpf(cpf);
        verify(addressService, times(1)).updateAddressOfUser(cpf, fakeAddressUpdateDTO);

        assertEquals(fakeAddressUpdatedDTO, result);
    }
}