package desafio_alura_foro_hub.foro_hub.service;


import desafio_alura_foro_hub.foro_hub.comentario.Comentario;
import desafio_alura_foro_hub.foro_hub.comentario.ComentarioRegistro;
import desafio_alura_foro_hub.foro_hub.repository.ComentarioRepository;
import desafio_alura_foro_hub.foro_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public Comentario crear(ComentarioRegistro datos) {
        Comentario comentario = convertirAComentario(datos);
        comentarioRepository.save(comentario);
        return comentario;
    }

    private Comentario convertirAComentario(ComentarioRegistro datos) {
        Comentario comentario = new Comentario();
        comentario.setTitulo(datos.getTitulo());
        comentario.setContenido(datos.getContenido());
        comentario.setFecha(datos.getFecha().atStartOfDay());
        comentario.setEstadoComentario(datos.getEstado());
        comentario.setUsuario(usuarioRepository.findById(datos.getUsuario().getId()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado")));
        comentario.setNombreCurso(datos.getNombreCurso());
        return comentario;
    }
}
