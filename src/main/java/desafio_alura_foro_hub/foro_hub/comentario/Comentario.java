package desafio_alura_foro_hub.foro_hub.comentario;

import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoComentario estadoComentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String nombreCurso;

    public Comentario(ComentarioRegistro comentarioRegistro) {
        this.titulo = comentarioRegistro.getTitulo();
        this.contenido = comentarioRegistro.getContenido();
        this.fecha = comentarioRegistro.getFecha().atStartOfDay();
        this.estadoComentario = comentarioRegistro.getEstado();
    }

    public void actualizarDatos(ComentarioRegistro comentarioRegistro) {
        if (comentarioRegistro.getTitulo() != null && !comentarioRegistro.getTitulo().isEmpty()) {
            this.titulo = comentarioRegistro.getTitulo();
        }
        if (comentarioRegistro.getContenido() != null && !comentarioRegistro.getContenido().isEmpty()) {
            this.contenido = comentarioRegistro.getContenido();
        }
        if (comentarioRegistro.getNombreCurso() != null && !comentarioRegistro.getNombreCurso().isEmpty()) {
            this.nombreCurso = comentarioRegistro.getNombreCurso();
        }
    }

}


