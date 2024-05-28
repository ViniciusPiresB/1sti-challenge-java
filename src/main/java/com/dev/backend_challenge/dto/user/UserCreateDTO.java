package com.dev.backend_challenge.dto.user;

import com.dev.backend_challenge.dto.address.AddressCreateDTO;
import com.dev.backend_challenge.enums.Status;
import com.dev.backend_challenge.enums.TypeUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "12345678910")
    @NotBlank
    @CPF
    private String cpf;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "John Doe")
    @NotBlank
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "pass1234")
    @NotBlank
    @Size(min=8)
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2000-07-15")
    @NotNull
    @Past
    private LocalDate birth;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @NumberFormat
    private TypeUser typeUser = TypeUser.USER;

    @NotNull
    private AddressCreateDTO address;
}
