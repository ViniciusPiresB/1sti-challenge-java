package com.dev.backend_challenge.dto.user;

import com.dev.backend_challenge.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateDTO {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "John Doe")
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "pass1234")
    @Size(min=8)
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "2000-07-15")
    @Past
    private LocalDate birth;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NumberFormat
    private Integer typeUser;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE | DELETED")
    private Status status = Status.ACTIVE;


}
