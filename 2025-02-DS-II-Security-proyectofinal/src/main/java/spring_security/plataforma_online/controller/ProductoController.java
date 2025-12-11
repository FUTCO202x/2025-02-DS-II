package spring_security.plataforma_online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring_security.plataforma_online.model.Producto;
import spring_security.plataforma_online.repository.ProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listByStock(@RequestParam(name = "stockThreshold", required = false, defaultValue = "999999") int stockThreshold) {
        if (stockThreshold == 999999) {
            return productoRepository.findAll();
        }
        return productoRepository.findByStockLessThanEqual(stockThreshold);
    }
}
