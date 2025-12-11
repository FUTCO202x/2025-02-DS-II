package spring_security.plataforma_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.plataforma_online.model.CarritoDeCompras;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<CarritoDeCompras, Long> {
    Optional<CarritoDeCompras> findByIdCarritoAndUsuario_IdUsuario(Long idCarrito, Long idUsuario);
}
