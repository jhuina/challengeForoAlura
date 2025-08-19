package cl.foro.alura.huina.foro.domain.topico;

import cl.foro.alura.huina.foro.domain.curso.Curso;
import cl.foro.alura.huina.foro.domain.curso.DatosDetalleCurso;

import java.time.LocalDateTime;

public record DatosListarTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosDetalleCurso curso
) {
    public DatosListarTopicos (Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosDetalleCurso(topico.getCurso())
        );
    }
}
