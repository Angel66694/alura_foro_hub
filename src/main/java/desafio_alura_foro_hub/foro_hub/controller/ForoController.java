package desafio_alura_foro_hub.foro_hub.controller;

import desafio_alura_foro_hub.foro_hub.comentario.Comentario;
import desafio_alura_foro_hub.foro_hub.comentario.ComentarioRegistro;
import desafio_alura_foro_hub.foro_hub.dto.DatoActualizarUsuario;
import desafio_alura_foro_hub.foro_hub.dto.DatosListaUsuario;
import desafio_alura_foro_hub.foro_hub.dto.DatosRegistroUsuario;
import desafio_alura_foro_hub.foro_hub.repository.ComentarioRepository;
import desafio_alura_foro_hub.foro_hub.repository.UsuarioRepository;
import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/comentario")
public class ForoController {

    private UsuarioRepository usuarioUnoRepository;
    private ComentarioRepository comentarioRepository;

    @PostMapping("/comentarios")
    public ResponseEntity<Comentario> crearComentario(@RequestBody @Valid ComentarioRegistro datos, UriComponentsBuilder uriBuilder) {
        Comentario comentario = comentarioRepository.save(new Comentario(new ComentarioRegistro()));
        comentario.setTitulo(datos.getTitulo());
        comentario.setContenido(datos.getContenido());
        comentario.setFecha(datos.getFecha().atStartOfDay());
        comentario.setEstadoComentario(datos.getEstado());
        comentario.setUsuario(usuarioUnoRepository.findById(datos.getUsuario().getId()).orElse(null));
        comentario.setNombreCurso(datos.getNombreCurso());

        comentarioRepository.save(comentario);

        URI uri = uriBuilder.path("/contenido/{id}").buildAndExpand(comentario.getId()).toUri();
        return ResponseEntity.created(uri).body(comentario);
    }

    @GetMapping("/usuario_comenta")
    public Page<DatosListaUsuario> listarUsuarios(Pageable pageable) {
        return usuarioUnoRepository.findByUsuarioActivoTrue(pageable)
                .map(usuario -> DatosListaUsuario.fromUsuario(usuario));
    }

    @PostMapping("/usuarios")
    @Transactional
    @Operation(
            summary = "Crea un usuario",
            description = "Requiere nombre, email y clave.",
            tags = {"post"}
    )
    public ResponseEntity<DatosListaUsuario> crearUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setLogin(datosRegistroUsuario.getLogin());
        nuevoUsuario.setNombre(datosRegistroUsuario.getNombre());
        nuevoUsuario.setEmail(datosRegistroUsuario.getEmail());
        nuevoUsuario.setContrasena(datosRegistroUsuario.getClave());
        nuevoUsuario.setUsuarioActivo(true);
        usuarioUnoRepository.save(nuevoUsuario);

        URI url = uriComponentsBuilder.path("/contenido/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();

        DatosListaUsuario datosListaUsuario = DatosListaUsuario.fromUsuario(nuevoUsuario);

        return ResponseEntity.created(url).body(datosListaUsuario);
    }

    @PutMapping("/usuarios")
    @Transactional
    public ResponseEntity<DatoActualizarUsuario> actualizarUsuario(@RequestBody @Valid DatoActualizarUsuario datoActualizarUsuario) {
        Usuario usuario = usuarioUnoRepository.findById(datoActualizarUsuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.actualizarDatos(datoActualizarUsuario);
        return ResponseEntity.ok(datoActualizarUsuario);
    }

    @DeleteMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioUnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.desactivaUsuario();
        return ResponseEntity.noContent().build();
    }
}
