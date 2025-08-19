package cl.foro.alura.huina.foro.domain.topico;

import cl.foro.alura.huina.foro.domain.curso.DatosDetalleCurso;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        DatosDetalleCurso curso
) {
}
