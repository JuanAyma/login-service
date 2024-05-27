package unmsm.authservice.User.dto.request;

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
