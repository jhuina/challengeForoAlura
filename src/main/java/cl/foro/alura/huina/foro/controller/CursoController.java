package cl.foro.alura.huina.foro.controller;

import cl.foro.alura.huina.foro.domain.curso.Curso;
import cl.foro.alura.huina.foro.domain.curso.CursoRepository;
import cl.foro.alura.huina.foro.domain.curso.DatosDetalleCurso;
import cl.foro.alura.huina.foro.domain.curso.DatosRegistroCurso;
import cl.foro.alura.huina.foro.domain.usuarios.AutenticacionRepository;
import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import cl.foro.alura.huina.foro.domain.usuarios.DatosDetalleUsuario;
import cl.foro.alura.huina.foro.domain.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository repository;


    @Transactional
    @PostMapping
    public ResponseEntity crearCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder, Authentication authentication){

        // Obtenemos el usuario autenticado del contexto de seguridad
        Autentificacion usuarioAutenticado = (Autentificacion) authentication.getPrincipal();
        var curso = new Curso(datos, usuarioAutenticado);


        repository.save(curso);
        var uri = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));    }
}
