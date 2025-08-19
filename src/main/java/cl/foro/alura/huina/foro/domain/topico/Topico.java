package cl.foro.alura.huina.foro.domain.topico;

import cl.foro.alura.huina.foro.domain.curso.Curso;
import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import cl.foro.alura.huina.foro.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status = true;
    private String titulo;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autentificacion autentificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    @JsonBackReference
    private Curso curso;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();



    public Topico(@Valid DatosRegistroTopico datos, Autentificacion autentificacion, Curso curso) {
        this.id = null;
        this.status = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autentificacion = autentificacion;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
    }


    public void actualizarInformaciones(@Valid DatosActualizarTopico datos) {

        if (datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }

    }

    public void eliminar() {
        this.status = false;
    }
}
