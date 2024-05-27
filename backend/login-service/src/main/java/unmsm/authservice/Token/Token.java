package unmsm.authservice.Token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unmsm.authservice.User.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private Boolean expired;
    private Boolean revoked;
    @ManyToOne(fetch = FetchType.LAZY) // fetch lazy significa que no se traerá la información del usuario a menos que se llame a este atributo
    @JoinColumn(name = "user_id")
    private User user;
}
