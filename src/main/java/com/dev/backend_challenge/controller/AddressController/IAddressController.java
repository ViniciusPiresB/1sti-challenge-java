package com.dev.backend_challenge.controller.AddressController;

import com.dev.backend_challenge.dto.Address.AddressDTO;
import com.dev.backend_challenge.dto.Address.AddressUpdateDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IAddressController {

    @Operation(summary = "Update address of a user", description = "Update an address of a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated address."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<AddressDTO> updateAddressOfUser(@PathVariable String userId, @Valid @RequestBody AddressUpdateDTO addressUpdateDTO) throws JsonMappingException;

    @Operation(summary = "List Addresses", description = "List all addresses in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of addresses."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<List<AddressDTO>> findAll();
}
