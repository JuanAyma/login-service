package unmsm.loginservice.User.dto.resquest;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoRequest {
    Integer id;
    String username;
    String firstname;
    String lastname;
    String country;
}
