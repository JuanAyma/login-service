package unmsm.loginservice.User.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unmsm.loginservice.Jwt.JwtService;
import unmsm.loginservice.User.entity.User;
import unmsm.loginservice.User.entity.UserRole;
import unmsm.loginservice.User.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest loginRequest) {
        //La siguiente linea es la que se encarga de autenticar al usuario
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
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
        userRepository.save(user);

        //Se encargará de devolver el token una vez que el usuario se haya registrado
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
