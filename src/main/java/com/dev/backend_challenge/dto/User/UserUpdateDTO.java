package com.dev.backend_challenge.dto.User;

import com.dev.backend_challenge.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "John Doe")
    @NotBlank
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "pass1234")
    @NotBlank
    @Size(min=8)
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ARRUMAR DEPOIS")
    @NotNull
    @Past
    private LocalDate birth;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull
    @NumberFormat
    private Integer typeUser;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE | DELETED")
    @NotNull
    private Status status = Status.ACTIVE;
}
