package cl.foro.alura.huina.foro.domain.topico;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        Long cursoId
) {
    public DatosDetalleTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso() != null ? topico.getCurso().getId() : null
        );
    }
}
