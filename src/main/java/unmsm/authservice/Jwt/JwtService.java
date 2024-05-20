package unmsm.authservice.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    //Encryption Key Generator
    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    // Generar una nueva clave segura para HMAC-SHA256
    //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims) //esto es para agregar datos adicionales al token
                .setSubject(user.getUsername()) //esto es para agregar el nombre de usuario
                .setIssuedAt(new java.util.Date(System.currentTimeMillis())) //esto es para agregar la fecha de creación del token
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //esto es para agregar la fecha de expiración del token después de 24 horas
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //esto es para agregar el algoritmo de encriptación
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        //Con Decoders.BASE64.decode() se decodifica la clave secreta que está en base64
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        //byte[] keyBytes = Decoders.BASE64.decode(String.valueOf(key));
        return Keys.hmacShaKeyFor(keyBytes); //crear una nueva instancia de la clave secreta
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public  <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) { //extractAllClaims
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
