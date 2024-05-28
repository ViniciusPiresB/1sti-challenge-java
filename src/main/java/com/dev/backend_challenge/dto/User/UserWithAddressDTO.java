package com.dev.backend_challenge.dto.User;

import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
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
    private LocalDate birth;
    private Status status = Status.ACTIVE;
    private TypeUser typeUser;
    private AddressDTO address;
}
