package cl.foro.alura.huina.foro.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
       @NotBlank String nombre,
       @NotBlank @Email String login,
       @NotBlank String contrasena
) {
    public DatosRegistroUsuario (Usuario usuario){
        this(
                usuario.getNombre(),
                usuario.getLogin(),
                usuario.getContrasena()
        );
    }
}
