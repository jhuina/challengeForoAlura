package cl.foro.alura.huina.foro.domain.curso;

import cl.foro.alura.huina.foro.domain.topico.Topico;
import cl.foro.alura.huina.foro.domain.usuarios.Autentificacion;
import cl.foro.alura.huina.foro.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Autentificacion usuario;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Topico> topicos;

    public Curso(@Valid DatosRegistroCurso datos, Autentificacion usuarioAutenticado) {
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
        this.usuario = usuarioAutenticado;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Autentificacion getUsuario() { return usuario; }
    public void setUsuario(Autentificacion usuario) { this.usuario = usuario; }



    public Curso(@Valid DatosRegistroCurso datos){
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

}
