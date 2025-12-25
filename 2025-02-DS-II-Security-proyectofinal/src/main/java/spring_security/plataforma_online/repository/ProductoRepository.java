package spring_security.plataforma_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.plataforma_online.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockLessThanEqual(int stock);
}
