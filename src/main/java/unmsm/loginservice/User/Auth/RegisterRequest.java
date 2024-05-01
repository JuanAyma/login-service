package unmsm.loginservice.User.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;
    String phone;
    String country;
    String role;
}
