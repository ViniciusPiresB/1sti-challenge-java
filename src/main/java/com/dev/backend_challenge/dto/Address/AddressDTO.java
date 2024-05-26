package com.dev.backend_challenge.dto.Address;

import com.dev.backend_challenge.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private int id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String cep;
    private String state;
    private User user;
}
