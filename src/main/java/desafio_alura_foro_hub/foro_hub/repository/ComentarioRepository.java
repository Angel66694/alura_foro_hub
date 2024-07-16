package desafio_alura_foro_hub.foro_hub.repository;


import desafio_alura_foro_hub.foro_hub.comentario.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    boolean existsByTituloAndContenido(String titulo, String contenido);

    @Query("SELECT c FROM Comentario c WHERE FUNCTION('YEAR', c.fecha) = :year")
    Page<Comentario> findByFechaYear(@Param("year") int year, Pageable pageable);
}
