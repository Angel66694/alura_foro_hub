package desafio_alura_foro_hub.foro_hub.usuario;

import desafio_alura_foro_hub.foro_hub.comentario.Comentario;
import desafio_alura_foro_hub.foro_hub.dto.DatoActualizarUsuario;
import desafio_alura_foro_hub.foro_hub.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "usuario_activo", nullable = false)
    private boolean usuarioActivo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.login = datosRegistroUsuario.getLogin();
        this.nombre = datosRegistroUsuario.getNombre();
        this.email = datosRegistroUsuario.getEmail();
        this.contrasena = datosRegistroUsuario.getClave();
        this.usuarioActivo = true;
    }

    public void actualizarDatos(DatoActualizarUsuario datoActualizarUsuario) {
        if (datoActualizarUsuario.getNombre() != null) {
            this.nombre = datoActualizarUsuario.getNombre();
        }
        if (datoActualizarUsuario.getClave() != null) {
            this.contrasena = datoActualizarUsuario.getClave();
        }
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
        comentario.setUsuario(this);
    }

    public void desactivaUsuario() {
        this.usuarioActivo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarioActivo;
    }
}
