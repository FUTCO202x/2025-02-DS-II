package spring_security.plataforma_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.plataforma_online.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
