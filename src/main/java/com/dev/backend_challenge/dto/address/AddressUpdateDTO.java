package com.dev.backend_challenge.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
