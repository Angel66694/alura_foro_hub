package desafio_alura_foro_hub.foro_hub.dto;

import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosListaUsuario(
        @NotNull
        String nombre,
        @NotNull
                @Email
        String email,
        @NotNull

        String clave
) {
    public static DatosListaUsuario fromUsuario(Usuario usuario) {
        return new DatosListaUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
    }
}
