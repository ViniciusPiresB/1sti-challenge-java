package com.dev.backend_challenge.dto.User;

import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String cpf;
    private LocalDate birth;
    private Status status = Status.ACTIVE;
    private TypeUser typeUser;
}
