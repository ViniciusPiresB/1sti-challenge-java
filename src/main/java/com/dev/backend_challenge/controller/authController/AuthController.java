package com.dev.backend_challenge.controller.authController;

import com.dev.backend_challenge.dto.auth.AuthDTO;
import com.dev.backend_challenge.dto.auth.LoginResponseDTO;
import com.dev.backend_challenge.entity.User;
import com.dev.backend_challenge.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Auth", description = "Authorization Route")
@RequestMapping("/auth")
public class AuthController implements IAuthController {
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO){
        var userPass = new UsernamePasswordAuthenticationToken(authDTO.cpf(), authDTO.password());
        var auth = this.authenticationManager.authenticate(userPass);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
