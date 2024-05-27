package unmsm.authservice.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200",
                        "http://localhost:3000"
                )
                .allowedMethods("*")//("GET", "POST", "PUT", "DELETE", "HEAD")
                .maxAge(3600) // Sirve para indicar el tiempo que el navegador debe almacenar en caché la información de la solicitud de verificación previa.
                .allowedHeaders("*") // Sirve para indicar los encabezados que se pueden usar en la solicitud.
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }
}
/*
Por defecto, @CrossOrigin permite:

todos los orígenes
todas las cabeceras
todos los métodos HTTP especificados en la anotación @RequestMapping maxAge de 30 minutos
Para personalizar el comportamiento, la anotación admite los siguientes atributos:
origins:
lista de orígenes permitidos. Su valor se coloca en la cabecera Access-Control-Allow-Origin
de las respuestas tanto previa verificacion como en las respuestas. El "*" o indefinido (por defecto) significa que todos los orígenes están permitidos.
allowedHeaders:
lista de cabeceras de solicitud que pueden utilizarse durante la solicitud actual. Su valor se utiliza en la cabecera de
respuesta Access-Control-Allow-Headers de la verificación previa. El "*" o indefinido (por defecto) significa que todas las cabeceras solicitadas por el cliente están permitidas.
methods:
lista de métodos de petición HTTP admitidos. Si no se define (por defecto), se utilizan los métodos definidos por @RequestMapping.
exposedHeaders:
lista de cabeceras de respuesta a las que el navegador permitirá acceder al cliente. Por defecto, es una lista vacía.
allowCredentials:
determina si el navegador debe incluir alguna cookie asociada a la petición. Por defecto, se permiten las credenciales.
exposedHeaders:
 lista de cabeceras de respuesta a las que el navegador permitirá acceder al cliente. Por defecto, es una lista vacía.
allowCredentials:
 determina si el navegador debe incluir alguna cookie asociada a la petición. Por defecto, se permiten las credenciales.
maxAge:
 se refiere a la antigüedad máxima (en segundos) de la duración de la caché para la previa verificacion de la cabecera
Access-Control-Max-Age de la respuesta. El valor predeterminado es 1800 segundos o 30 minutos.
 */
