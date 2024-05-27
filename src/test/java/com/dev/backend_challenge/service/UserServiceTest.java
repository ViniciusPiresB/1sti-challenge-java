package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.Address.AddressCreateDTO;
import com.dev.backend_challenge.dto.User.UserCreateDTO;
import com.dev.backend_challenge.dto.User.UserDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
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

    private final User fakeUser = User.builder()
            .id("a3718843-5456-4482-9c97-a20f78cbd44e")
            .name("Test User 1")
            .password("encodedPassword")
            .birth(LocalDate.now())
            .status(Status.ACTIVE)
            .typeUser(0)
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
            .typeUser(0)
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
            .typeUser(0)
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

        String activeUserCpf = "12345678910";

        UserDTO result = userService.create(fakeUserCreateDTO, activeUserCpf);

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
}