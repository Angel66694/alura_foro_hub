package desafio_alura_foro_hub.foro_hub.dto;


import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DatoActualizarUsuario {

    @NotNull
    Long id;
    private String nombre;
    private String clave;


    public DatoActualizarUsuario() {

    }

    public DatoActualizarUsuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
