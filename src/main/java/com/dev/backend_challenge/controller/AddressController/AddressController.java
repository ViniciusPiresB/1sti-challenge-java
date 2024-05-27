package com.dev.backend_challenge.controller.AddressController;

import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.dto.Address.AddressUpdateDTO;
import com.dev.backend_challenge.service.AddressService;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@CrossOrigin
@AllArgsConstructor
@RestController
@Validated
public class AddressController {
    private final AddressService addressService;

    @PatchMapping("/{userId}")
    public ResponseEntity<AddressDTO> updateAddressOfUser(@PathVariable String userId, @Valid @RequestBody AddressUpdateDTO addressUpdateDTO) throws JsonMappingException {
        return new ResponseEntity<>(this.addressService.updateAddressOfUser(userId, addressUpdateDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AddressDTO>> findAll(){
        return new ResponseEntity<>(this.addressService.findAll(), HttpStatus.OK);
    }
}
