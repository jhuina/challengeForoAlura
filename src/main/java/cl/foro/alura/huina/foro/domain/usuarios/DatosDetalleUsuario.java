package cl.foro.alura.huina.foro.domain.usuarios;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String login,
        String contrasena
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
          usuario.getId(),
          usuario.getNombre(),
          usuario.getLogin(),
          usuario.getContrasena()
        );
    }
}
