package desafio_alura_foro_hub.foro_hub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DatosRegistroUsuario {
        @NotNull
        private String login;
        @NotNull
        private String nombre;

        @NotNull
        @Email
        private String email;

        @NotNull
        private String clave;

        public DatosRegistroUsuario() {

        }

        public DatosRegistroUsuario(String login, String nombre, String email, String clave) {
                this.login = login;
                this.nombre = nombre;
                this.email = email;
                this.clave = clave;
        }

}
