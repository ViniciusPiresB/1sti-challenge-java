package com.dev.backend_challenge.service;

import com.dev.backend_challenge.dto.User.UserCreateDTO;
import com.dev.backend_challenge.dto.User.UserDTO;
import com.dev.backend_challenge.entity.Address;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final AddressService addressService;
    private final BCryptPasswordEncoder encoder;

    public UserDTO create(UserCreateDTO userCreateDTO, String activeUserCpf){
        User user = this.objectMapper.convertValue(userCreateDTO, User.class);

        String encodedPassword = this.encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setCreatedBy(activeUserCpf);

        this.userRepository.save(user);

        addressService.create(userCreateDTO.getAddress(), user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }
}
