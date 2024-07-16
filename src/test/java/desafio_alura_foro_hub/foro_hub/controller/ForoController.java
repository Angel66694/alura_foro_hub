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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/contenido")
public class ForoController {

    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ForoController(UsuarioRepository usuarioRepository, ComentarioRepository comentarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody @Valid ComentarioRegistro datos, UriComponentsBuilder uriBuilder) {
        Comentario comentario = new Comentario();
        comentario.setTitulo(datos.getTitulo());
        comentario.setContenido(datos.getContenido());
        comentario.setFecha(datos.getFecha().atStartOfDay());
        comentario.setEstadoComentario(datos.getEstado());
        comentario.setUsuario(usuarioRepository.findById(datos.getUsuario().getId()).orElse(null));
        comentario.setNombreCurso(datos.getNombreCurso());

        comentarioRepository.save(comentario);

        URI uri = uriBuilder.path("/contenido/{id}").buildAndExpand(comentario.getId()).toUri();
        return ResponseEntity.created(uri).body(comentario);
    }

    @GetMapping("/usuario_comenta")
    public Page<DatosListaUsuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findByUsuarioActivoTrue(pageable).map(DatosListaUsuario::new);
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
        usuarioRepository.save(nuevoUsuario);

        URI url = uriComponentsBuilder.path("/contenido/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosListaUsuario(nuevoUsuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatoActualizarUsuario> actualizarUsuario(@RequestBody @Valid DatoActualizarUsuario datoActualizarUsuario) {
        Usuario usuario = usuarioRepository.findById(datoActualizarUsuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.actualizarDatos(datoActualizarUsuario);
        return ResponseEntity.ok(datoActualizarUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.desactivaUsuario();
        return ResponseEntity.noContent().build();
    }
}
