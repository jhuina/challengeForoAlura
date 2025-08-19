package cl.foro.alura.huina.foro.security;

import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.toke.secret}")
    private String secret;
    public String generarToken(Autentificacion autentificacion){

        try {
            var algorismo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API foro")
                    .withSubject(autentificacion.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorismo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Error al generar un token", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }

    public String getSubjet(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API foro")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }
}
