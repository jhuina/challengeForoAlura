package cl.foro.alura.huina.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosRegistroTopico(String titulo, String mensaje, Long cursoId  ) {
   public DatosRegistroTopico(Topico topico){
       this(
               topico.getTitulo(),
               topico.getMensaje(),
               topico.getCurso() != null ? topico.getCurso().getId() : null
       );
   }

}
