package unmsm.authservice.Auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unmsm.authservice.Auth.dto.request.RegisterRequest;
import unmsm.authservice.Auth.dto.response.AuthResponse;
import unmsm.authservice.Auth.service.AuthService;
import unmsm.authservice.Auth.dto.request.LoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
