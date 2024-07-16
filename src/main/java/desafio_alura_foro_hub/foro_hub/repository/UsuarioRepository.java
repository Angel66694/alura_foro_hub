package desafio_alura_foro_hub.foro_hub.repository;


import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByUsuarioActivoTrue(Pageable pageable);

    UserDetails findByLogin(String username);
}
