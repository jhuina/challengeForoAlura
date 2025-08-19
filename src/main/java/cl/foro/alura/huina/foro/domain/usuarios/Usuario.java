package cl.foro.alura.huina.foro.domain.usuarios;

import cl.foro.alura.huina.foro.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table (name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nombre;

    private String login;

    private String contrasena;



    public Usuario(@Valid DatosRegistroUsuario datos){
        this.id = null;
        this.nombre = datos.nombre();
        this.login = datos.login();
        this.contrasena = datos.contrasena();
    }


}
