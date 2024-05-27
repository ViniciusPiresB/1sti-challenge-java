package com.dev.backend_challenge.dto.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDTO {
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Rua RA 7")
    private String street;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "3")
    private String number;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Boa Vista")
    private String district;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "São Paulo")
    private String city;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "SP")
    private String state;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "01165497")
    private String cep;
}
