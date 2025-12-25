package spring_security.plataforma_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.plataforma_online.model.OrdenDeCompra;

public interface OrdenRepository extends JpaRepository<OrdenDeCompra, Long> {
}
