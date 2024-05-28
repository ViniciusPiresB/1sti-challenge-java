package com.dev.backend_challenge.dto.user;

import com.dev.backend_challenge.dto.address.AddressDTO;
import com.dev.backend_challenge.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWithAddressDTO {
    private String id;
    private String name;
    private String cpf;
    private LocalDate birth;
    private Status status = Status.ACTIVE;
    private Integer typeUser = 0;
    private AddressDTO address;
}
