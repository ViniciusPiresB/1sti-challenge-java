package com.dev.backend_challenge.controller.userController;

import com.dev.backend_challenge.dto.user.*;
import com.dev.backend_challenge.service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@Tag(name = "User", description = "CRUD of users")
@AllArgsConstructor
@RestController
@Validated
public class UserController implements IUserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(this.userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserDTO> findOne(@PathVariable String cpf){
        return new ResponseEntity<>(this.userService.findOne(cpf), HttpStatus.OK);
    }

    @GetMapping("/first-user/get")
    public ResponseEntity<UserWithPassDTO> getFirstUser(){
        return new ResponseEntity<>(this.userService.firstAccess(), HttpStatus.CREATED);
    }

    @GetMapping("/address/{cpf}")
    public ResponseEntity<UserWithAddressDTO> findUserWithAddress(@PathVariable String cpf){
        return new ResponseEntity<>(this.userService.findUserWithAddress(cpf), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<UserDTO> update(@PathVariable String cpf, @Valid @RequestBody UserUpdateDTO userUpdateDTO) throws JsonMappingException {
            return new ResponseEntity<>(this.userService.update(userUpdateDTO, cpf), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<UserDTO> remove(@PathVariable String cpf){
        return new ResponseEntity<>(this.userService.delete(cpf), HttpStatus.OK);
    }
}
