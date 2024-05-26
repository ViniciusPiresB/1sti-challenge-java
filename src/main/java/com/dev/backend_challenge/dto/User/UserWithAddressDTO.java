package com.dev.backend_challenge.dto.User;

import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithAddressDTO {
    private String id;
    private String name;
    private String password;
    private LocalDate birth;
    private Status status = Status.ACTIVE;
    private Integer typeUser = 0;
    private AddressDTO address;
}
