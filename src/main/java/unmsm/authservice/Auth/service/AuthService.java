package unmsm.authservice.Auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unmsm.authservice.Jwt.JwtService;
import unmsm.authservice.Auth.dto.request.LoginRequest;
import unmsm.authservice.Auth.dto.request.RegisterRequest;
import unmsm.authservice.Auth.dto.response.AuthResponse;
import unmsm.authservice.Token.Token;
import unmsm.authservice.Token.TokenRepository;
import unmsm.authservice.Token.TokenType;
import unmsm.authservice.User.entity.User;
import unmsm.authservice.User.entity.UserRole;
import unmsm.authservice.User.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) { //AuthenticationResponse
        //La siguiente linea es la que se encarga de autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .phone(registerRequest.getPhone())
                .country(registerRequest.getCountry())
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        //Se encargará de devolver el token una vez que el usuario se haya registrado
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

}
