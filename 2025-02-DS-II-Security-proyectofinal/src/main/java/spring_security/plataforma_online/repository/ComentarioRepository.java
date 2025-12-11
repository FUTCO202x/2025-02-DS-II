package spring_security.plataforma_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.plataforma_online.model.Comentario;

import java.time.LocalDate;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByFechaAfter(LocalDate fecha);
}
