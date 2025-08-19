package cl.foro.alura.huina.foro.controller;

import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import cl.foro.alura.huina.foro.domain.usuarios.DatosAutenticacion;
import cl.foro.alura.huina.foro.security.DatosTokenJWT;
import cl.foro.alura.huina.foro.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var token = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autenticacion = manager.authenticate(token);

        var usuario = (Autentificacion) autenticacion.getPrincipal();

        var tokenJWT = tokenService.generarToken(usuario);

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));

    }
}
