package desafio_alura_foro_hub.foro_hub.comentario;


import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ComentarioRegistro {

    @NotNull
    private String titulo;

    @NotNull
    private String contenido;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private EstadoComentario estado;

    @NotNull
    private Usuario usuario;

    @NotNull
    private String nombreCurso;

    // Constructor
    public ComentarioRegistro() {
    }
}
