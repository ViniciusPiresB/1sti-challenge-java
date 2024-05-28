package com.dev.backend_challenge.controller.AuthController;

import com.dev.backend_challenge.dto.auth.AuthDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @Operation(summary = "Login a user", description = "Login of a user in system")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return JWT token."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Missing CPF or password."),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)), description = "CPF or password invalid."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO);
}
