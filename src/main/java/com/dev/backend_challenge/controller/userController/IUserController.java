package com.dev.backend_challenge.controller.userController;

import com.dev.backend_challenge.dto.user.UserCreateDTO;
import com.dev.backend_challenge.dto.user.UserDTO;
import com.dev.backend_challenge.dto.user.UserUpdateDTO;
import com.dev.backend_challenge.dto.user.UserWithAddressDTO;
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

public interface IUserController {
    @Operation(summary = "Create User", description = "Create a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Return created user."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO);

    @Operation(summary = "Get User by cpf", description = "Get user by cpf in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return user with a given cpf."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Entity not found"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> findOne(@PathVariable String cpf);

    @Operation(summary = "Get User by cpf with address", description = "Get user by cpf with address in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return user with a given cpf with address."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Entity not found"),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserWithAddressDTO> findUserWithAddress(@PathVariable String cpf);

    @Operation(summary = "List Users", description = "List all users in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of users."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<List<UserDTO>> findAll();

    @Operation(summary = "Update User", description = "Update a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated user."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> update(@PathVariable String cpf, @Valid @RequestBody UserUpdateDTO userUpdateDTO) throws JsonMappingException;

    @Operation(summary = "Delete User", description = "Delete a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(hidden = true)), description = "Missing token or not enough permission."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> remove(@PathVariable String cpf);
}
