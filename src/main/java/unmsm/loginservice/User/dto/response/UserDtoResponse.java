package unmsm.loginservice.User.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDtoResponse {
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String email;
}
