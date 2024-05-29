package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.address.AddressDTO;
import com.dev.backend_challenge.dto.address.AddressUpdateDTO;
import com.dev.backend_challenge.dto.user.*;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.enums.ErrorEnum;
import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
import com.dev.backend_challenge.exception.ValidationException;
import com.dev.backend_challenge.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final AddressService addressService;
    private final PasswordEncoder encoder;

    public UserDTO create(UserCreateDTO userCreateDTO, String activeUserCpf){
        User user = this.objectMapper.convertValue(userCreateDTO, User.class);

        String encodedPassword = this.encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setCreatedBy(activeUserCpf);

        this.userRepository.save(user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public List<UserDTO> findAll(){
        return this.userRepository.findAll().stream().map(user -> objectMapper.convertValue(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO findOne(String cpf){
        User user = this.getUser(cpf);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public UserWithPassDTO firstAccess(){
        User user = this.userRepository.findByCpf("44441015046");

        if(user != null) throw new BadCredentialsException("First user was already created.");

        UserCreateDTO firstUserCreateDTO = UserCreateDTO.builder()
                .name("ROOT USER")
                .cpf("44441015046")
                .password("12345678")
                .birth(LocalDate.now())
                .typeUser(TypeUser.ROOT)
                .address(null)
                .build();

        UserDTO firstUserDTO = this.create(firstUserCreateDTO, firstUserCreateDTO.getCpf());

        UserWithPassDTO firstUserWithPassDTO = this.objectMapper.convertValue(firstUserDTO, UserWithPassDTO.class);
        firstUserWithPassDTO.setPassword(firstUserCreateDTO.getPassword());

        return firstUserWithPassDTO;
    }

    public UserWithAddressDTO findUserWithAddress(String cpf){
        User user = this.getUser(cpf);

        AddressDTO addressDTO = this.addressService.findOneByUserId(user.getId());

        UserWithAddressDTO userWithAddressDTO = this.objectMapper.convertValue(user, UserWithAddressDTO.class);

        userWithAddressDTO.setAddress(addressDTO);

        return userWithAddressDTO;
    }

    public AddressDTO updateAddress(String cpf, AddressUpdateDTO addressUpdateDTO) throws JsonMappingException {
        User user = this.getUser(cpf);

        AddressDTO updatedAddress = this.addressService.updateAddressOfUser(cpf, addressUpdateDTO);

        return updatedAddress;
    }

    public UserDTO update(UserUpdateDTO userUpdateDTO, String cpf) throws JsonMappingException {
        User user = this.getUser(cpf);

        this.objectMapper.updateValue(user, userUpdateDTO);

        User updatedUser =  this.userRepository.save(user);

        return this.objectMapper.convertValue(updatedUser, UserDTO.class);
    }

    public UserDTO delete(String cpf, String activeUserCpf) {
        User user = this.getUser(cpf);

        user.setStatus(Status.DELETED);
        user.setDeletedBy(activeUserCpf);
        user.setDeletedAt(LocalDate.now());

        this.userRepository.save(user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    private User getUser(String cpf){
        User user = this.userRepository.findByCpf(cpf);

        if(user == null) throw new ValidationException(ErrorEnum.NOT_FOUND);

        if(user.getStatus() == Status.DELETED) throw new ValidationException((ErrorEnum.DELETED_ENTITY));

        return user;
    }
}
