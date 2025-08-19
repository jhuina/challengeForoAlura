package cl.foro.alura.huina.foro.controller;

import cl.foro.alura.huina.foro.domain.curso.Curso;
import cl.foro.alura.huina.foro.domain.curso.CursoRepository;
import cl.foro.alura.huina.foro.domain.topico.*;
import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Transactional
    @PostMapping
    public ResponseEntity nuevoTopico (@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder, Authentication authentication){
        Autentificacion usuarioAutenticado = (Autentificacion) authentication.getPrincipal();
        // Buscar curso por id
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        var topico = new Topico(datos, usuarioAutenticado, curso);

        repository.save(topico);
        var uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopicos>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){
        var page = repository.findAllByStatusTrue(paginacion).map(DatosListarTopicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRegistroTopico(topico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity ActualizarTopico(@RequestBody @Valid DatosActualizarTopico datos,  Authentication authentication){

        var topico =repository.findById(datos.id()).orElse(null);
        if (topico == null){
            return ResponseEntity.notFound().build();
        }
        Autentificacion usuarioAutenticado = (Autentificacion) authentication.getPrincipal();
        if(!topico.getAutentificacion().getId().equals(usuarioAutenticado.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene permisos para actualizar este tópico.");
        }
        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

}
