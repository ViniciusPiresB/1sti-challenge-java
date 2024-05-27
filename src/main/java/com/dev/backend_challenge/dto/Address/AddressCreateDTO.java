package com.dev.backend_challenge.dto.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateDTO {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Rua RA 7")
    @NotBlank
    private String street;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotBlank
    private String number;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Boa Vista")
    @NotBlank
    private String district;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "São Paulo")
    @NotBlank
    private String city;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "SP")
    @NotBlank
    private String state;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "01165497")
    @NotBlank
    private String cep;
}
